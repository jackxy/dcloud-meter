package net.xdclass.controller.common;

import jakarta.annotation.Resource;
import net.xdclass.req.common.ProjectDelReq;
import net.xdclass.req.common.ProjectSaveReq;
import net.xdclass.req.common.ProjectUpdateReq;
import net.xdclass.service.common.ProjectService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 获取列表
     * 通过GET请求访问/list接口，获取项目列表
     *
     * @return 列表数据
     */
    @GetMapping("/list")
    public JsonData list(){
        return JsonData.buildSuccess(projectService.list());
    }



    /**
     * 保存项目
     * 使用POST方法请求/save接口，用于保存项目信息
     *
     * @param req 项目保存请求对象
     * @return 保存结果的JsonData对象
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ProjectSaveReq req){
        return JsonData.buildSuccess(projectService.save(req));
    }



    /**
     * 更新项目
     * 使用POST方法请求"/update"接口，用于更新项目信息
     *
     * @param req 项目更新请求对象
     * @return 更新结果的JsonData对象
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ProjectUpdateReq req){
        return JsonData.buildSuccess(projectService.update(req));
    }



    /**
     * 删除项目
     * 使用POST方法请求"/del"接口，接收一个ProjectDelReq对象作为参数
     * ProjectDelReq对象必须包含一个项目ID（id）
     * 调用projectService的delete方法，将项目ID作为参数传入
     * 返回删除结果的JsonData对象
     */
    @PostMapping("/del")
    public JsonData delete(@RequestBody ProjectDelReq req){
        return JsonData.buildSuccess(projectService.delete(req.getId()));
    }



}
