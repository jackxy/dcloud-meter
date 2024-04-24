package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.dto.api.ApiCaseDTO;
import net.xdclass.dto.api.ApiCaseModuleDTO;
import net.xdclass.mapper.ApiCaseMapper;
import net.xdclass.mapper.ApiCaseModuleMapper;
import net.xdclass.mapper.ApiCaseStepMapper;
import net.xdclass.model.*;
import net.xdclass.req.api.ApiCaseModuleDelReq;
import net.xdclass.req.api.ApiCaseModuleSaveReq;
import net.xdclass.req.api.ApiCaseModuleUpdateReq;
import net.xdclass.service.api.ApiCaseModuleService;
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
public class ApiCaseModuleServiceImpl implements ApiCaseModuleService {

    @Resource
    private ApiCaseModuleMapper apiCaseModuleMapper;

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private ApiCaseStepMapper apiCaseStepMapper;

    /**
     * 根据项目ID获取ApiCaseModuleDTO列表
     *
     * @param projectId 项目ID
     * @return ApiCaseModuleDTO列表
     */
    @Override
    public List<ApiCaseModuleDTO> list(Long projectId) {
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getProjectId, projectId);
        List<ApiCaseModuleDO> apiCaseModuleDOS = apiCaseModuleMapper.selectList(queryWrapper);
        List<ApiCaseModuleDTO> list = SpringBeanUtil.copyProperties(apiCaseModuleDOS, ApiCaseModuleDTO.class);
        list.forEach(apiCaseModuleDTO -> {
            LambdaQueryWrapper<ApiCaseDO> caseQueryWrapper = new LambdaQueryWrapper<>();
            caseQueryWrapper.eq(ApiCaseDO::getModuleId, apiCaseModuleDTO.getId());
            List<ApiCaseDO> apiCaseDOS = apiCaseMapper.selectList(caseQueryWrapper);
            apiCaseModuleDTO.setList(SpringBeanUtil.copyProperties(apiCaseDOS, ApiCaseDTO.class));
        });
        return list;
    }


    @Override
    public ApiCaseModuleDTO getById(Long projectId, Long moduleId) {
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getProjectId, projectId).eq(ApiCaseModuleDO::getId, moduleId);
        ApiCaseModuleDO apiCaseModuleDO = apiCaseModuleMapper.selectOne(queryWrapper);
        ApiCaseModuleDTO apiCaseModuleDTO = SpringBeanUtil.copyProperties(apiCaseModuleDO, ApiCaseModuleDTO.class);

        //查询模块下的用例列表
        LambdaQueryWrapper<ApiCaseDO> apiCaseQueryWrapper = new LambdaQueryWrapper<>();
        apiCaseQueryWrapper.eq(ApiCaseDO::getModuleId, apiCaseModuleDTO.getId());
        List<ApiCaseDO> apiCaseDOS = apiCaseMapper.selectList(apiCaseQueryWrapper);
        List<ApiCaseDTO> apiCaseDTOS = SpringBeanUtil.copyProperties(apiCaseDOS, ApiCaseDTO.class);
        apiCaseModuleDTO.setList(apiCaseDTOS);
        return apiCaseModuleDTO;
    }

    @Override
    public int save(ApiCaseModuleSaveReq req) {
        ApiCaseModuleDO apiCaseModuleDO = SpringBeanUtil.copyProperties(req, ApiCaseModuleDO.class);
        return apiCaseModuleMapper.insert(apiCaseModuleDO);
    }

    @Override
    public int update(ApiCaseModuleUpdateReq req) {
        ApiCaseModuleDO apiCaseModuleDO = SpringBeanUtil.copyProperties(req, ApiCaseModuleDO.class);
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getId, req.getId()).eq(ApiCaseModuleDO::getProjectId, req.getProjectId());
        return apiCaseModuleMapper.update(apiCaseModuleDO, queryWrapper);

    }

    @Override
    public int del(ApiCaseModuleDelReq req) {
        //删除模块
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>(ApiCaseModuleDO.class);
        queryWrapper.eq(ApiCaseModuleDO::getProjectId, req.getProjectId()).eq(ApiCaseModuleDO::getId, req.getId());
        int delete = apiCaseModuleMapper.delete(queryWrapper);

        //删除模块下的用例
        LambdaQueryWrapper<ApiCaseDO> caseQueryWapper = new LambdaQueryWrapper<>(ApiCaseDO.class);
        caseQueryWapper.select(ApiCaseDO::getId).eq(ApiCaseDO::getModuleId, req.getId());
        List<Long> caseIdList = apiCaseMapper.selectList(caseQueryWapper).stream().map(ApiCaseDO::getId).toList();
        if(!caseIdList.isEmpty()){
            apiCaseMapper.deleteBatchIds(caseIdList);
        }

        //删除用例下的步骤
        LambdaQueryWrapper<ApiCaseStepDO> stepQueryWapper = new LambdaQueryWrapper<>(ApiCaseStepDO.class);
        stepQueryWapper.select(ApiCaseStepDO::getId).in(ApiCaseStepDO::getCaseId, caseIdList);
        List<Long> stepIdList = apiCaseStepMapper.selectList(stepQueryWapper).stream().map(ApiCaseStepDO::getId).toList();
        if(!stepIdList.isEmpty()){
            apiCaseStepMapper.deleteBatchIds(stepIdList);
        }


        return delete;
    }
}
