package net.xdclass.config;

import cn.dev33.satoken.stp.StpInterface;
import jakarta.annotation.Resource;
import net.xdclass.service.PermissionService;
import net.xdclass.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        if(loginId == null){
            return new ArrayList<>();
        }
        Long accountId = Long.parseLong(loginId.toString());
        List<String> list = permissionService.findPermissionCodeList(accountId);
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        if(loginId == null){
            return new ArrayList<>();
        }
        Long accountId = Long.parseLong(loginId.toString());
        List<String> list = roleService.findRoleCodeList(accountId);
        return list;
    }
}
