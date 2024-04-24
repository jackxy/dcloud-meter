package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.dto.api.ApiDTO;
import net.xdclass.mapper.ApiMapper;
import net.xdclass.model.ApiDO;
import net.xdclass.req.api.ApiDelReq;
import net.xdclass.req.api.ApiSaveReq;
import net.xdclass.req.api.ApiUpdateReq;
import net.xdclass.service.api.ApiService;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    private ApiMapper apiMapper;

    @Override
    public ApiDTO getById(Long projectId, Long id) {
        //根据projectId和id查找api对象
        LambdaQueryWrapper<ApiDO> queryWrapper = new LambdaQueryWrapper<ApiDO>();
        queryWrapper.eq(ApiDO::getProjectId, projectId).eq(ApiDO::getId, id);
        ApiDO apiDO = apiMapper.selectOne(queryWrapper);
        return SpringBeanUtil.copyProperties(apiDO, ApiDTO.class);
    }

    @Override
    public int save(ApiSaveReq req) {
        ApiDO apiDO = SpringBeanUtil.copyProperties(req, ApiDO.class);
        return apiMapper.insert(apiDO);
    }

    @Override
    public int update(ApiUpdateReq req) {
        ApiDO apiDO = SpringBeanUtil.copyProperties(req, ApiDO.class);
        LambdaQueryWrapper<ApiDO> queryWrapper = new LambdaQueryWrapper<ApiDO>();
        queryWrapper.eq(ApiDO::getProjectId, apiDO.getProjectId()).eq(ApiDO::getId, apiDO.getId());
        return apiMapper.update(apiDO,queryWrapper);
    }

    @Override
    public int delete(ApiDelReq req) {

        LambdaQueryWrapper<ApiDO> queryWrapper = new LambdaQueryWrapper<ApiDO>();
        queryWrapper.eq(ApiDO::getProjectId, req.getProjectId()).eq(ApiDO::getId, req.getId());
        return apiMapper.delete(queryWrapper);
    }
}
