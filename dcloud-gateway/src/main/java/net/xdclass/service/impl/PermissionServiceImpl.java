package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import net.xdclass.mapper.PermissionMapper;
import net.xdclass.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findPermissionCodeList(Long accountId) {

        return permissionMapper.findPermissionCodeList(accountId);
    }
}
