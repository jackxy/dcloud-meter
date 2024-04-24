package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import net.xdclass.mapper.RoleMapper;
import net.xdclass.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> findRoleCodeList(Long accountId) {
        return roleMapper.findRoleCodeList(accountId);
    }
}
