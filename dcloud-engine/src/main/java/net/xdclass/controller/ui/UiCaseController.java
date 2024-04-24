package net.xdclass.controller.ui;

import io.swagger.v3.core.util.Json;
import jakarta.annotation.Resource;
import net.xdclass.dto.dto.UiCaseDTO;
import net.xdclass.req.ui.UiCaseDelReq;
import net.xdclass.req.ui.UiCaseSaveReq;
import net.xdclass.req.ui.UiCaseUpdateReq;
import net.xdclass.service.ui.UiCaseService;
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
@RequestMapping("/api/v1/ui_case")
public class UiCaseController {

    @Resource
    private UiCaseService uiCaseService;

    @RequestMapping("/update")
    public JsonData update(@RequestBody UiCaseUpdateReq req)
    {
        return JsonData.buildSuccess(uiCaseService.update(req));
    }

    @RequestMapping("/del")
    public JsonData delete(@RequestBody UiCaseDelReq req)
    {
        return JsonData.buildSuccess(uiCaseService.delete(req));
    }



    @RequestMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId)
    {
        UiCaseDTO uiCaseDTO = uiCaseService.find(projectId, caseId);
        return JsonData.buildSuccess(uiCaseDTO);
    }

    /**
     * 新增
     */
    @RequestMapping("/save")
    public JsonData save(@RequestBody UiCaseSaveReq req)
    {
        return JsonData.buildSuccess(uiCaseService.save(req));
    }



    @GetMapping("/execute")
    public JsonData execute(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId){

        JsonData jsonData = uiCaseService.execute(projectId, caseId);

        return  jsonData;
    }

}
