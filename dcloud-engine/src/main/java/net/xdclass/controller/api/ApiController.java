package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiDelReq;
import net.xdclass.req.api.ApiSaveReq;
import net.xdclass.req.api.ApiUpdateReq;
import net.xdclass.service.api.ApiService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/api")
public class ApiController {

    @Resource
    private ApiService apiService;


    /**
     * 根据projectId和id查找
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("id") Long id) {
        return JsonData.buildSuccess(apiService.getById(projectId, id));
    }

    /**
     * 保存接口
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ApiSaveReq req) {
        return JsonData.buildSuccess(apiService.save(req));
    }

    /**
     * 修改接口
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ApiUpdateReq req) {
        return JsonData.buildSuccess(apiService.update(req));
    }

    /**
     * 删除接口
     */
    @PostMapping("/delete")
    public JsonData delete(@RequestBody ApiDelReq req) {
        return JsonData.buildSuccess(apiService.delete(req));
    }
}
