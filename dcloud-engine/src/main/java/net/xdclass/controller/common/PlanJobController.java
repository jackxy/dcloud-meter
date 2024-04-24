package net.xdclass.controller.common;

import jakarta.annotation.Resource;
import net.xdclass.req.common.PlanJobDelReq;
import net.xdclass.req.common.PlanJobPageReq;
import net.xdclass.req.common.PlanJobSaveReq;
import net.xdclass.req.common.PlanJobUpdateReq;
import net.xdclass.service.common.PlanJobService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/plan_job")
public class PlanJobController {


    @Resource
    private PlanJobService planJobService;


    /**
     * 分页接口
     */
    @PostMapping("page")
    public JsonData page(@RequestBody PlanJobPageReq req){
        Map<String,Object> pageInfo = planJobService.page(req);
        return JsonData.buildSuccess(pageInfo);
    }


    /**
     * 新增
     */
    @PostMapping("save")
    public JsonData save(@RequestBody PlanJobSaveReq req){

        int rows = planJobService.save(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 更新
     */
    @PostMapping("update")
    public JsonData update(@RequestBody PlanJobUpdateReq req){

        int rows = planJobService.update(req);
        return JsonData.buildSuccess(rows);
    }

    /**
     * 删除
     */
    @PostMapping("del")
    public JsonData del(@RequestBody PlanJobDelReq req){

        int rows = planJobService.del(req);
        return JsonData.buildSuccess(rows);
    }


}
