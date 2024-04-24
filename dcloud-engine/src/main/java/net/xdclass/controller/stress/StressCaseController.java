package net.xdclass.controller.stress;

import jakarta.annotation.Resource;
import net.xdclass.req.stress.StressCaseDelReq;
import net.xdclass.req.stress.StressCaseSaveReq;
import net.xdclass.req.stress.StressCaseUpdateReq;
import net.xdclass.service.stress.StressCaseService;
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
@RequestMapping("/api/v1/stress_case")
public class StressCaseController {

    @Resource
    private StressCaseService stressCaseService;


    @RequestMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId){
        return JsonData.buildSuccess(stressCaseService.findById(projectId,caseId));
    }

    @PostMapping("del")
    public JsonData delete(@RequestBody StressCaseDelReq req){
        return JsonData.buildSuccess(stressCaseService.delete(req.getProjectId(),req.getId()));
    }

    @PostMapping("save")
    public JsonData save(@RequestBody StressCaseSaveReq req){
        return JsonData.buildSuccess(stressCaseService.save(req));
    }

    @RequestMapping("update")
    public JsonData update(@RequestBody StressCaseUpdateReq req){

        return JsonData.buildSuccess(stressCaseService.update(req));
    }

    @GetMapping("/execute")
    public JsonData execute(@RequestParam("projectId") Long projectId,@RequestParam("id") Long caseId){
        stressCaseService.execute(projectId,caseId);
        return JsonData.buildSuccess();
    }

}
