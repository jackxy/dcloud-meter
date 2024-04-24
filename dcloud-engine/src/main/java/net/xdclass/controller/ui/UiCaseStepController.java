package net.xdclass.controller.ui;

import jakarta.annotation.Resource;
import net.xdclass.req.ui.UiCaseStepDelReq;
import net.xdclass.req.ui.UiCaseStepSaveReq;
import net.xdclass.req.ui.UiCaseStepUpdateReq;
import net.xdclass.service.ui.UiCaseStepService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/ui_case_step")
public class UiCaseStepController {

    @Resource
    private UiCaseStepService uiCaseStepService;

    @RequestMapping("/save")
    public JsonData save(@RequestBody UiCaseStepSaveReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.save(req));
    }

    @RequestMapping("/update")
    public JsonData update(@RequestBody UiCaseStepUpdateReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.update(req));
    }

    @RequestMapping("/del")
    public JsonData delete(@RequestBody UiCaseStepDelReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.delete(req));
    }
}
