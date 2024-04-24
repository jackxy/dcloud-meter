package net.xdclass.controller;

import jakarta.annotation.Resource;
import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;
import net.xdclass.dto.RoleDTO;
import net.xdclass.service.RoleService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

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
public class RoleController {

    @Resource
    private RoleService roleService;


    /**
     * 给某个角色新增权限
     */
    @PostMapping("/api/permit/v1/role/addPermission")
    public JsonData addPermission(@RequestBody RoleAddPermissionReq req) {
        int rows = roleService.addPermission(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 某个角色移除权限
     */
    @PostMapping("/api/permit/v1/role/delPermission")
    public JsonData delPermission(@RequestBody RoleDelPermissionReq req) {
        int rows = roleService.delPermission(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 给某个账号新增角色
     */
    @PostMapping("/api/permit/v1/role/addRoleByAccountId")
    public JsonData addRoleByAccountId(@RequestBody AccountRoleAddReq req) {
        int rows = roleService.addRoleByAccountId(req);
        return JsonData.buildSuccess(rows);
    }

    /**
     * 给某个账号移除角色
     */
    @PostMapping("/api/permit/v1/role/delRoleByAccountId")
    public JsonData delRoleByAccountId(@RequestBody AccountRoleDelReq req) {
        int rows = roleService.delRoleByAccountId(req);
        return JsonData.buildSuccess(rows);
    }

    /**
     * 查看全部角色列表
     */
    @GetMapping("/api/permit/v1/role/list")
    public JsonData list() {
        List<RoleDTO> list = roleService.list();
        return JsonData.buildSuccess(list);
    }


    /**
     * 新增角色
     */
    @PostMapping("/api/permit/v1/role/add")
    public JsonData addRole(@RequestBody RoleAddReq addReq){

        int rows = roleService.addRole(addReq);
        return JsonData.buildSuccess(rows);
    }

    /**
     * 删除角色
     */
    @PostMapping("/api/permit/v1/role/delete")
    public JsonData deleteRole(@RequestBody RoleDelReq delReq){

        int rows = roleService.deleteRole(delReq.getId());
        return JsonData.buildSuccess(rows);
    }


    /**
     * 查找某个账号的角色和权限
     */
    @GetMapping("/api/permit/v1/role/findRoleByAccountId")
    public JsonData findRoleByAccountId(@RequestParam("accountId") Long accountId) {
        AccountDTO accountDTO = roleService.getAccountWithRoleByAccountId(accountId);
        return JsonData.buildSuccess(accountDTO);
    }


}
