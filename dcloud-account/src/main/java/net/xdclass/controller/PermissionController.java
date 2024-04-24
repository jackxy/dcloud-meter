package net.xdclass.controller;

import jakarta.annotation.Resource;
import net.xdclass.dto.PermissionDTO;
import net.xdclass.service.PermissionService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
public class PermissionController {

    @Resource
    private PermissionService permissionService;


    /**
     * 获取全部权限
     */
    @GetMapping("/api/permit/v1/permission/list")
    public JsonData getAllPermission() {
        List<PermissionDTO> list = permissionService.list();
        return JsonData.buildSuccess(list);
    }



}
