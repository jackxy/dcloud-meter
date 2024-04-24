package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.mapper.ApiCaseStepMapper;
import net.xdclass.model.ApiCaseStepDO;
import net.xdclass.req.api.ApiCaseStepSaveReq;
import net.xdclass.req.api.ApiCaseStepUpdateReq;
import net.xdclass.service.api.ApiCaseStepService;
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
public class ApiCaseStepServiceImpl implements ApiCaseStepService {

    @Resource
    private ApiCaseStepMapper apiCaseStepMapper;

    @Override
    public int save(ApiCaseStepSaveReq req) {
        ApiCaseStepDO apiCaseStepDO = SpringBeanUtil.copyProperties(req, ApiCaseStepDO.class);
        return apiCaseStepMapper.insert(apiCaseStepDO);
    }

    @Override
    public int update(ApiCaseStepUpdateReq req) {
        ApiCaseStepDO apiCaseStepDO = SpringBeanUtil.copyProperties(req, ApiCaseStepDO.class);
        LambdaQueryWrapper<ApiCaseStepDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseStepDO::getId, req.getId()).eq(ApiCaseStepDO::getProjectId,req.getProjectId());
        return apiCaseStepMapper.update(apiCaseStepDO,queryWrapper);
    }

    @Override
    public int del(Long projectId, Long id) {
            LambdaQueryWrapper<ApiCaseStepDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ApiCaseStepDO::getId, id).eq(ApiCaseStepDO::getProjectId,projectId);
            return apiCaseStepMapper.delete(queryWrapper);
    }
}
