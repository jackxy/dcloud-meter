package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiCaseStepDelReq;
import net.xdclass.req.api.ApiCaseStepSaveReq;
import net.xdclass.req.api.ApiCaseStepUpdateReq;
import net.xdclass.service.api.ApiCaseStepService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/api_case_step")
public class ApiCaseStepController {

    @Resource
    private ApiCaseStepService apiCaseStepService;

    /**
     * 保存接口
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ApiCaseStepSaveReq req) {
        return JsonData.buildSuccess(apiCaseStepService.save(req));
    }

    /**
     * 修改接口
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ApiCaseStepUpdateReq req) {
        return JsonData.buildSuccess(apiCaseStepService.update(req));
    }


    /**
     * 删除接口
     */
    @PostMapping("/delete")
    public JsonData delete(@RequestBody ApiCaseStepDelReq req) {
        return JsonData.buildSuccess(apiCaseStepService.del(req.getProjectId(), req.getId()));
    }

}
