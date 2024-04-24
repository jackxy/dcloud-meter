package net.xdclass.controller.common;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.xdclass.util.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {


    private static Set<String> TOKNE_SET = new HashSet<>();

    @GetMapping("/api/v2/test/detail")
    @ResponseBody
    public JsonData detail(Long id) {
        return JsonData.buildSuccess("传入id=" + id);
    }

    /**
     * form表单登录，生成令牌
     *
     * @param mail
     * @param pwd
     * @return
     */
    @PostMapping("/api/v2/test/login_form")
    @ResponseBody
    public JsonData loginApi(String mail, String pwd) {

        if ("xdclass".equals(mail) && "123456".equals(pwd)) {
            String token = IdUtil.simpleUUID();
            TOKNE_SET.add(token);
            return JsonData.buildSuccess(token);
        }
        return JsonData.buildError("登录失败，账号密码错误");
    }


    /**
     * 模拟下单，需要登录token，然后增加订单号
     *
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/api/v2/test/buy")
    @ResponseBody
    public JsonData buy(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (TOKNE_SET.contains(token)) {
            map.put("order_id", IdUtil.simpleUUID());
            return JsonData.buildSuccess(map);
        } else {
            return JsonData.buildError("请登录，再下单");
        }
    }


    /**
     * 模拟文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/api/v2/test/upload")
    @ResponseBody
    public JsonData upload(@RequestParam("file") MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            Map<String, String> map = new HashMap<>();
            map.put("originalFilename", originalFilename);
            map.put("contentType", contentType);
            return JsonData.buildSuccess(map);
        } else {
            return JsonData.buildError("上传文件失败,文件为空");
        }
    }


    //======================================================================



    /**
     * form表单提交，不能json
     * @param mail
     * @param pwd
     * @return
     */
    @RequestMapping("/api/v1/test/login_form")
    @ResponseBody
    public JsonData login(String mail, String pwd){

        if(mail.startsWith("a")){
            return JsonData.buildError("账号错误");
        }
        return JsonData.buildSuccess("mail="+mail+",pwd="+pwd);
    }




    /**
     * json提交
     * @param mail
     * @param pwd
     * @return
     */
    @PostMapping("/api/v1/test/pay_json")
    @ResponseBody
    public JsonData pay(@RequestBody Map<String,String> map) {

        String id = map.get("id");
        String amount = map.get("amount");
        return JsonData.buildSuccess("id="+id+",amount="+amount);
    }


    /**
     * json提交, 加上随机睡眠时间
     * @param mail
     * @param pwd
     * @return
     */
    @PostMapping("/api/v1/test/pay_json_sleep")
    @ResponseBody
    public JsonData paySleep(@RequestBody Map<String,String> map) {

        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            String id = map.get("id");
            String amount = map.get("amount");
            return JsonData.buildSuccess("id="+id+",amount="+amount+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * get方式提交
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query")
    @ResponseBody
    public JsonData queryDetail(Long id){
        return JsonData.buildSuccess("id="+id);
    }


    /**
     * get方式，随机睡眠时间
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query_sleep")
    @ResponseBody
    public JsonData querySleep(Long id){
        try {
            int value = RandomUtil.randomInt(1000);
            TimeUnit.MICROSECONDS.sleep(value);
            return JsonData.buildSuccess("id="+id+",sleep="+value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * get方式，id取模3是0后则http状态码500
     * @param id
     * @return
     */
    @GetMapping("/api/v1/test/query_error_code")
    @ResponseBody
    public JsonData queryError(Long id,  HttpServletResponse response){

        if(id % 3 == 0){
            response.setStatus(500);
        }
        return JsonData.buildSuccess("id="+id);
    }

}
