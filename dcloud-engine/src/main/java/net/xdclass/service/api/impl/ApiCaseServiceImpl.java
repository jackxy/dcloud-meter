package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.ApiCaseResultDTO;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.api.ApiCaseDTO;
import net.xdclass.dto.ApiCaseStepDTO;
import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.feign.ReportFeignService;
import net.xdclass.mapper.ApiCaseMapper;
import net.xdclass.mapper.ApiCaseStepMapper;
import net.xdclass.model.ApiCaseDO;
import net.xdclass.model.ApiCaseStepDO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.api.ApiCaseSaveReq;
import net.xdclass.req.api.ApiCaseUpdateReq;
import net.xdclass.service.api.ApiCaseService;
import net.xdclass.service.api.core.ApiExecuteEngine;
import net.xdclass.util.JsonData;
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
public class ApiCaseServiceImpl implements ApiCaseService {

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private ApiCaseStepMapper apiCaseStepMapper;

    @Resource
    private ReportFeignService reportFeignService;

    @Override
    public ApiCaseDTO getById(Long projectId, Long id) {
        LambdaQueryWrapper<ApiCaseDO> queryWrapper = new LambdaQueryWrapper<ApiCaseDO>();
        queryWrapper.eq(ApiCaseDO::getProjectId, projectId).eq(ApiCaseDO::getId, id);
        ApiCaseDO apiCaseDO = apiCaseMapper.selectOne(queryWrapper);
        ApiCaseDTO apiCaseDTO = SpringBeanUtil.copyProperties(apiCaseDO, ApiCaseDTO.class);

        //查找关联的步骤
        LambdaQueryWrapper<ApiCaseStepDO> caseStepQueryWrapper = new LambdaQueryWrapper<ApiCaseStepDO>();
        caseStepQueryWrapper.eq(ApiCaseStepDO::getCaseId, apiCaseDO.getId()).orderByAsc(ApiCaseStepDO::getNum);
        List<ApiCaseStepDO> apiCaseStepDOS = apiCaseStepMapper.selectList(caseStepQueryWrapper);
        List<ApiCaseStepDTO> apiCaseStepDTOS = SpringBeanUtil.copyProperties(apiCaseStepDOS, ApiCaseStepDTO.class);

        apiCaseDTO.setList(apiCaseStepDTOS);
        return apiCaseDTO;
    }

    @Override
    public int save(ApiCaseSaveReq req) {
        ApiCaseDO apiCaseDO = SpringBeanUtil.copyProperties(req, ApiCaseDO.class);
        int insert = apiCaseMapper.insert(apiCaseDO);
        //保存用例下的步骤
        req.getList().forEach(step -> {
            ApiCaseStepDO apiCaseStepDO = SpringBeanUtil.copyProperties(step, ApiCaseStepDO.class);
            apiCaseStepDO.setCaseId(apiCaseDO.getId());
            apiCaseStepMapper.insert(apiCaseStepDO);
        });

        return insert;
    }

    @Override
    public int update(ApiCaseUpdateReq req) {
        ApiCaseDO apiCaseDO = SpringBeanUtil.copyProperties(req, ApiCaseDO.class);
        LambdaQueryWrapper<ApiCaseDO> queryWrapper = new LambdaQueryWrapper<ApiCaseDO>();
        queryWrapper.eq(ApiCaseDO::getProjectId, req.getProjectId()).eq(ApiCaseDO::getId, req.getId());
        return apiCaseMapper.update(apiCaseDO, queryWrapper);
    }

    @Override
    public int del(Long projectId, Long id) {
        LambdaQueryWrapper<ApiCaseDO> queryWrapper = new LambdaQueryWrapper<ApiCaseDO>();
        queryWrapper.eq(ApiCaseDO::getProjectId, projectId).eq(ApiCaseDO::getId, id);
        int delete = apiCaseMapper.delete(queryWrapper);

        //删除用例下的步骤
        LambdaQueryWrapper<ApiCaseStepDO> queryWrapperStep = new LambdaQueryWrapper<ApiCaseStepDO>();
        queryWrapperStep.eq(ApiCaseStepDO::getCaseId, id);
        apiCaseStepMapper.delete(queryWrapperStep);
        return delete;
    }

    /**
     * 查询用例
     * 查询用例关联的步骤
     * 初始化测试报告
     * 执行自动化测试
     * 响应结果
     *
     * @param projectId
     * @param caseId
     * @return
     */
    @Override
    public JsonData execute(Long projectId, Long caseId) {
        LambdaQueryWrapper<ApiCaseDO> queryWrapper = new LambdaQueryWrapper<ApiCaseDO>();
        queryWrapper.eq(ApiCaseDO::getProjectId, projectId).eq(ApiCaseDO::getId, caseId);
        ApiCaseDO apiCaseDO = apiCaseMapper.selectOne(queryWrapper);
        if (apiCaseDO != null) {
            //查找用例关联的步骤
            LambdaQueryWrapper<ApiCaseStepDO> stepQueryWrapper = new LambdaQueryWrapper<ApiCaseStepDO>();
            stepQueryWrapper.eq(ApiCaseStepDO::getCaseId, apiCaseDO.getId()).orderByAsc(ApiCaseStepDO::getNum);
            List<ApiCaseStepDO> apiCaseStepDOS = apiCaseStepMapper.selectList(stepQueryWrapper);
            if(apiCaseStepDOS == null || apiCaseStepDOS.isEmpty()){
                throw new BizException(BizCodeEnum.API_CASE_STEP_IS_EMPTY);
            }
            //初始化测试报告
            ReportSaveReq reportSaveReq = ReportSaveReq.builder().projectId(apiCaseDO.getProjectId())
                    .caseId(apiCaseDO.getId())
                    .startTime(System.currentTimeMillis())
                    .executeState(ReportStateEnum.EXECUTING.name())
                    .name(apiCaseDO.getName())
                    .type(TestTypeEnum.API.name()).build();
            JsonData jsonData = reportFeignService.save(reportSaveReq);
            if(jsonData.isSuccess()){
                //执行用例
                ReportDTO reportDTO = jsonData.getData(ReportDTO.class);

                CaseInfoDTO caseInfoDTO = new CaseInfoDTO();
                caseInfoDTO.setId(apiCaseDO.getId());
                caseInfoDTO.setModuleId(apiCaseDO.getModuleId());
                caseInfoDTO.setName(apiCaseDO.getName());

                ApiExecuteEngine apiExecuteEngine = new ApiExecuteEngine(reportDTO);
                ApiCaseResultDTO  apiCaseResultDTO = apiExecuteEngine.execute(caseInfoDTO, apiCaseStepDOS);
                return JsonData.buildSuccess(apiCaseResultDTO);
            }else {
                log.error("API接口用例执行，初始化测试报告失败,{}",apiCaseDO);
                return JsonData.buildError("API接口用例执行，初始化测试报告失败");
            }
        } else {
            return JsonData.buildError("用例不存在");
        }

    }
}
