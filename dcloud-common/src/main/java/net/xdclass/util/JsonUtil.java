package net.xdclass.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Slf4j
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {

        //设置可用单引号
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        //序列化的时候序列对象的所有属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //下划线和驼峰互转
        //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        //如果是空对象的时候,不抛异常
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


    public static ObjectMapper get() {
        return MAPPER;
    }


    /**
     * 对象转为Json字符串
     * @param data
     * @return
     */
    public static String obj2Json(Object obj) {
        String jsonStr = null;
        try {
            jsonStr = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json格式化异常:{}",e);
        }
        return jsonStr;
    }
    /**
     * json字符串转为对象
     * @param str
     * @param valueType
     * @return
     */
    public static <T> T json2Obj(String jsonStr, Class<T> beanType) {
        T obj = null;
        try {
            obj = MAPPER.readValue(jsonStr, beanType);
        } catch (Exception e){
            log.error("json格式化异常:{}",e);
        }
        return obj;
    }

    /**
     * json数据转换成pojo对象list
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> json2List(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.error("json格式化异常:{}",e);
        }
        return new ArrayList<>(0);
    }



}