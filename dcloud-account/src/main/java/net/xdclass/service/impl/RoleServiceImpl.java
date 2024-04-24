package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;
import net.xdclass.dto.RoleDTO;
import net.xdclass.mapper.AccountRoleMapper;
import net.xdclass.mapper.RoleMapper;
import net.xdclass.mapper.RolePermissionMapper;
import net.xdclass.model.AccountRoleDO;
import net.xdclass.model.RoleDO;
import net.xdclass.model.RolePermissionDO;
import net.xdclass.service.RoleService;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private AccountRoleMapper accountRoleMapper;


    @Override
    public int addPermission(RoleAddPermissionReq req) {
        LambdaQueryWrapper<RolePermissionDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermissionDO::getRoleId, req.getRoleId())
                .eq(RolePermissionDO::getPermissionId, req.getPermissionId());

        Long selectCount = rolePermissionMapper.selectCount(queryWrapper);
        if(selectCount == 0){
            RolePermissionDO rolePermissionDO = new RolePermissionDO();
            rolePermissionDO.setRoleId(req.getRoleId());
            rolePermissionDO.setPermissionId(req.getPermissionId());
            return rolePermissionMapper.insert(rolePermissionDO);
        }
        return 0;
    }

    @Override
    public int delPermission(RoleDelPermissionReq req) {
        LambdaQueryWrapper<RolePermissionDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermissionDO::getRoleId, req.getRoleId())
                .eq(RolePermissionDO::getPermissionId, req.getPermissionId());
        return rolePermissionMapper.delete(queryWrapper);
    }

    @Override
    public int addRoleByAccountId(AccountRoleAddReq req) {
        //先查询有没有个角色
        LambdaQueryWrapper<AccountRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountRoleDO::getAccountId, req.getAccountId())
                .eq(AccountRoleDO::getRoleId, req.getRoleId());
        if(accountRoleMapper.selectCount(queryWrapper) == 0){
            AccountRoleDO accountRoleDO = new AccountRoleDO();
            accountRoleDO.setAccountId(req.getAccountId());
            accountRoleDO.setRoleId(req.getRoleId());
            return accountRoleMapper.insert(accountRoleDO);
        }
        return 0;
    }

    @Override
    public int delRoleByAccountId(AccountRoleDelReq req) {
        LambdaQueryWrapper<AccountRoleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountRoleDO::getAccountId, req.getAccountId())
                .eq(AccountRoleDO::getRoleId, req.getRoleId());
        return accountRoleMapper.delete(queryWrapper);
    }

    @Override
    public List<RoleDTO> list() {

        List<RoleDTO> list = roleMapper.listRoleWithPermission();

        return list;
    }

    @Override
    public int addRole(RoleAddReq addReq) {
        RoleDO roleDO = SpringBeanUtil.copyProperties(addReq, RoleDO.class);
        return roleMapper.insert(roleDO);
    }

    @Override
    public int deleteRole(Long id) {
        int rows = roleMapper.deleteById(id);
        if(rows>0){
            accountRoleMapper.delete(new LambdaQueryWrapper<AccountRoleDO>().eq(AccountRoleDO::getRoleId,id));
            rolePermissionMapper.delete(new LambdaQueryWrapper<RolePermissionDO>().eq(RolePermissionDO::getRoleId,id));
        }
        return rows;
    }

    @Override
    public AccountDTO getAccountWithRoleByAccountId(Long accountId) {
        AccountDTO accountDTO = roleMapper.findAccountWithRoleAndPermission(accountId);
        return accountDTO;
    }
}
