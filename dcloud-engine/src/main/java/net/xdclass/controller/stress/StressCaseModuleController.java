package net.xdclass.controller.stress;

import jakarta.annotation.Resource;
import net.xdclass.req.stress.StressCaseModuleDelReq;
import net.xdclass.req.stress.StressCaseModuleSaveReq;
import net.xdclass.req.stress.StressCaseModuleUpdateReq;
import net.xdclass.service.stress.StressCaseModuleService;
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
@RequestMapping("/api/v1/stress_case_module")
public class StressCaseModuleController {

    @Resource
    private StressCaseModuleService stressCaseModuleService;


    @GetMapping("list")
    public JsonData list(@RequestParam("projectId")Long projectId){
        return JsonData.buildSuccess(stressCaseModuleService.list(projectId));
    }

    @GetMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("moduleId") Long moduleId){
        return JsonData.buildSuccess(stressCaseModuleService.findById(projectId,moduleId));
    }


    @PostMapping("/del")
    public JsonData delete(@RequestBody StressCaseModuleDelReq req){
        return JsonData.buildSuccess(stressCaseModuleService.delete(req.getProjectId(),req.getId()));
    }
    @PostMapping("/save")
    public JsonData save(@RequestBody StressCaseModuleSaveReq req){
        return JsonData.buildSuccess(stressCaseModuleService.save(req));
    }

    @PostMapping("/update")
    public JsonData update(@RequestBody StressCaseModuleUpdateReq req){
        return JsonData.buildSuccess(stressCaseModuleService.update(req));
    }
}
