package net.xdclass.util;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.api.ApiJsonAssertionDTO;
import net.xdclass.enums.ApiAssertActionEnum;
import net.xdclass.enums.ApiAssertFieldFromEnum;
import net.xdclass.enums.ApiAssertTypeEnum;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.service.api.core.ApiRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Slf4j
/**
 * Api断言工具类
 */
public class ApiAssertionUtil {

    /**
     * 断言分发方法
     * @param action        断言类型
     * @param value         待断言的值
     * @param expectValue   预期值
     * @return              断言结果
     */
    public static boolean assertionDispatcher(String action, String value, String expectValue) {
        ApiAssertActionEnum actionEnum = ApiAssertActionEnum.valueOf(action);
        boolean result = switch (actionEnum) {
            case CONTAIN -> value.contains(expectValue);
            case NOT_CONTAIN -> !value.contains(expectValue);
            case EQUAL -> value.equals(expectValue);
            case NOT_EQUAL -> !value.equals(expectValue);
            case LESS_THEN -> Long.parseLong(value) < Long.parseLong(expectValue);
            case GREAT_THEN -> Long.parseLong(value) > Long.parseLong(expectValue);
            default -> throw new BizException(BizCodeEnum.API_OPERATION_UNSUPPORTED_ASSERTION);
        };

        //加判断，封装对象，AssertMsg{ msg = "", state=true | false}
//        if(result){
//            return
//        }else


        return result;
    }

    /**
     * 断言分发方法
     * @param request       Api请求对象
     * @param response      Api响应对象
     */
    public static void dispatcher(ApiRequest request, Response response) {
        if (StringUtils.isBlank(request.getAssertion())) {
            return;
        }

        for (ApiJsonAssertionDTO assertion : request.getAssertionList()) {
            boolean state = true;
            String express = assertion.getExpress();
            String expectValue = assertion.getValue();
            ApiAssertFieldFromEnum fieldFromEnum = ApiAssertFieldFromEnum.valueOf(assertion.getFrom());
            try {
                if (ApiAssertTypeEnum.JSONPATH.name().equals(assertion.getType())) {
                    // jsonpath解析
                    state = switch (fieldFromEnum) {
                        case RESPONSE_CODE -> {
                            String stringify = String.valueOf(response.getStatusCode());
                            Object responseCode = JsonPath.read(stringify, express);
                            yield assertionDispatcher(assertion.getAction(), String.valueOf(responseCode), expectValue);
                        }
                        case RESPONSE_HEADER -> {
                            String stringify = JsonUtil.obj2Json(response.getHeaders());
                            Object responseHeader = JsonPath.read(stringify, express);
                            yield assertionDispatcher(assertion.getAction(), String.valueOf(responseHeader), expectValue);
                        }
                        case RESPONSE_DATA -> {
                            String stringify = response.getBody().asString();
                            Object responseData = JsonPath.read(stringify, express);
                            yield assertionDispatcher(assertion.getAction(), String.valueOf(responseData), expectValue);
                        }
                        default -> {
                            log.error("不支持的断言来源:{}", fieldFromEnum);
                            throw new BizException(BizCodeEnum.API_OPERATION_UNSUPPORTED_FROM);
                        }
                    };

                } else if (ApiAssertTypeEnum.REGEXP.name().equals(assertion.getType())) {
                    // 正则解析
                    Pattern pattern = Pattern.compile(express);
                    state = switch (fieldFromEnum) {
                        case RESPONSE_CODE -> {
                            String stringify = String.valueOf(response.getStatusCode());
                            Matcher matcher = pattern.matcher(stringify);
                            yield matcher.find() && assertionDispatcher(assertion.getAction(), matcher.group(), expectValue);
                        }
                        case RESPONSE_HEADER -> {
                            String stringify = JsonUtil.obj2Json(response.getHeaders());
                            Matcher matcher = pattern.matcher(stringify);
                            yield matcher.find() && assertionDispatcher(assertion.getAction(), matcher.group(), expectValue);
                        }
                        case RESPONSE_DATA -> {
                            String stringify = response.getBody().asString();
                            Matcher matcher = pattern.matcher(stringify);
                            yield matcher.find() && assertionDispatcher(assertion.getAction(), matcher.group(), expectValue);
                        }
                        default -> {
                            log.error("不支持的断言来源:{}", fieldFromEnum);
                            throw new BizException(BizCodeEnum.API_OPERATION_UNSUPPORTED_FROM);
                        }
                    };
                }
                if (!state) {
                    // 断言失败
                    throw new BizException(BizCodeEnum.API_ASSERTION_FAILED);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException(BizCodeEnum.API_ASSERTION_FAILED);
            }
        }
    }
}
