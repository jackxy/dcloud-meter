package net.xdclass.controller.common;

import jakarta.annotation.Resource;
import net.xdclass.req.common.EnvironmentDelReq;
import net.xdclass.req.common.EnvironmentSaveReq;
import net.xdclass.req.common.EnvironmentUpdateReq;
import net.xdclass.service.common.EnvironmentService;
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
@RequestMapping("/api/v1/env")
public class EnvironmentController {

    @Resource
    private EnvironmentService environmentService;


    @GetMapping("/list")
    public JsonData list(@RequestParam("projectId")Long projectId){
        return JsonData.buildSuccess(environmentService.list(projectId));
    }


    @PostMapping("/save")
    public JsonData save(@RequestBody EnvironmentSaveReq req){
        return JsonData.buildSuccess(environmentService.save(req));
    }


    @PostMapping("/update")
    public JsonData update(@RequestBody EnvironmentUpdateReq req){
        return JsonData.buildSuccess(environmentService.update(req));
    }

    @PostMapping("/del")
    public JsonData delete(@RequestBody EnvironmentDelReq req){
        return JsonData.buildSuccess(environmentService.delete(req.getProjectId(),req.getId()));
    }
}
