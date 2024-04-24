package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiModuleDelReq;
import net.xdclass.req.api.ApiModuleSaveReq;
import net.xdclass.req.api.ApiModuleUpdateReq;
import net.xdclass.service.api.ApiModuleService;
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
@RequestMapping("/api/v1/api_module")
public class ApiModuleController {

    @Resource
    private ApiModuleService apiModuleService;

    @GetMapping("/list")
    public JsonData list(Long projectId) {
        return JsonData.buildSuccess(apiModuleService.list(projectId));
    }

    /**
     * 根据id查找
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("moduleId") Long moduleId) {
        return JsonData.buildSuccess(apiModuleService.getById(projectId,moduleId));
    }

    /**
     * 根据projectId和moduleId删除用例模块
     */
    @PostMapping("/delete")
    public JsonData delete(@RequestBody ApiModuleDelReq req) {
        return JsonData.buildSuccess(apiModuleService.delete(req.getId(),req.getProjectId()));
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ApiModuleSaveReq req) {
        return JsonData.buildSuccess(apiModuleService.save(req));
    }


    /**
     * 更新
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ApiModuleUpdateReq req) {
        return JsonData.buildSuccess(apiModuleService.update(req));
    }


}
