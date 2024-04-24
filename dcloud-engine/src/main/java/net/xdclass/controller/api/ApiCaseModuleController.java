package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiCaseModuleDelReq;
import net.xdclass.req.api.ApiCaseModuleSaveReq;
import net.xdclass.req.api.ApiCaseModuleUpdateReq;
import net.xdclass.service.api.ApiCaseModuleService;
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
@RequestMapping("/api/v1/api_case_module")
public class ApiCaseModuleController {

    @Resource
    private ApiCaseModuleService apiCaseModuleService;

    /**
     * 列表接口
     */
    @GetMapping("/list")
    public JsonData list(@RequestParam("projectId") Long projectId) {
        return JsonData.buildSuccess(apiCaseModuleService.list(projectId));
    }

    /**
     * find查找
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("moduleId") Long moduleId) {
        return JsonData.buildSuccess(apiCaseModuleService.getById(projectId, moduleId));
    }

    /**
     * save保存
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ApiCaseModuleSaveReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.save(req));
    }

    /**
     * update修改
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ApiCaseModuleUpdateReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.update(req));
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    public JsonData delete(@RequestBody ApiCaseModuleDelReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.del(req));
    }

}
