package net.xdclass.service.stress.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.stress.StressCaseDTO;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.enums.StressSourceTypeEnum;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.feign.ReportFeignService;
import net.xdclass.mapper.EnvironmentMapper;
import net.xdclass.mapper.StressCaseMapper;
import net.xdclass.model.EnvironmentDO;
import net.xdclass.model.StressCaseDO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.stress.StressCaseSaveReq;
import net.xdclass.req.stress.StressCaseUpdateReq;
import net.xdclass.service.stress.StressCaseService;
import net.xdclass.service.stress.core.BaseStressEngine;
import net.xdclass.service.stress.core.StressJmxEngine;
import net.xdclass.service.stress.core.StressSimpleEngine;
import net.xdclass.util.JsonData;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
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
public class StressCaseServiceImpl implements StressCaseService {

    @Resource
    private StressCaseMapper stressCaseMapper;

    @Resource
    private ReportFeignService reportFeignService;


    @Resource
    private ApplicationContext applicationContext;


    @Resource
    private EnvironmentMapper environmentMapper;

    @Override
    public StressCaseDTO findById(Long projectId, Long caseId) {
        LambdaQueryWrapper<StressCaseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseDO::getProjectId, projectId)
                .eq(StressCaseDO::getId, caseId);
        StressCaseDO stressCaseDO = stressCaseMapper.selectOne(queryWrapper);
        return SpringBeanUtil.copyProperties(stressCaseDO, StressCaseDTO.class);
    }

    @Override
    public int delete(Long projectId, Long id) {
        LambdaQueryWrapper<StressCaseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseDO::getProjectId, projectId)
                .eq(StressCaseDO::getId, id);
        return stressCaseMapper.delete(queryWrapper);
    }

    @Override
    public int save(StressCaseSaveReq req) {
        StressCaseDO stressCaseDO = SpringBeanUtil.copyProperties(req, StressCaseDO.class);
        return stressCaseMapper.insert(stressCaseDO);
    }

    @Override
    public int update(StressCaseUpdateReq req) {
        StressCaseDO stressCaseDO = SpringBeanUtil.copyProperties(req, StressCaseDO.class);
        LambdaQueryWrapper<StressCaseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseDO::getProjectId, stressCaseDO.getProjectId())
                .eq(StressCaseDO::getId, stressCaseDO.getId());
        return stressCaseMapper.update(stressCaseDO, queryWrapper);
    }

    /**
     * 执行用例
     * 【1】查询用例详情
     * 【2】初始化测试报告
     * 【3】判断压测类型 JMX、SIMPLE
     * 【4】初始化测试引擎
     * 【5】组装测试计划
     * 【6】执行压测
     * 【7】发送压测结果明细
     * 【8】压测完成清理数据
     * 【9】通知压测结束
     */
    @Override
    @Async("testExecutor")
    public void execute(Long projectId, Long caseId) {
        LambdaQueryWrapper<StressCaseDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseDO::getProjectId, projectId)
                .eq(StressCaseDO::getId, caseId);
        StressCaseDO stressCaseDO = stressCaseMapper.selectOne(queryWrapper);

        if (stressCaseDO != null) {
            //初始化测试报告
            ReportSaveReq reportSaveReq = ReportSaveReq.builder().projectId(stressCaseDO.getProjectId())
                    .caseId(stressCaseDO.getId())
                    .startTime(System.currentTimeMillis())
                    .executeState(ReportStateEnum.EXECUTING.name())
                    .name(stressCaseDO.getName())
                    .type(TestTypeEnum.STRESS.name())
                    .build();
            JsonData jsonData = reportFeignService.save(reportSaveReq);
            if (jsonData.isSuccess()) {
                ReportDTO reportDTO = jsonData.getData(ReportDTO.class);

                //判断压测类型 JMX、SIMPLE
                if (StressSourceTypeEnum.JMX.name().equalsIgnoreCase(stressCaseDO.getStressSourceType())) {
                    runJmxStressCase(stressCaseDO, reportDTO);
                } else if (StressSourceTypeEnum.SIMPLE.name().equalsIgnoreCase(stressCaseDO.getStressSourceType())) {

                    runSimpleStressCase(stressCaseDO, reportDTO);

                } else {
                    throw new BizException(BizCodeEnum.STRESS_UNSUPPORTED);
                }
            }
        }

    }

    private void runJmxStressCase(StressCaseDO stressCaseDO, ReportDTO reportDTO) {
        //创建引擎
        BaseStressEngine stressEngine = new StressJmxEngine(stressCaseDO,reportDTO,applicationContext);

        //运行压测
        stressEngine.startStressTest();

    }

    private void runSimpleStressCase(StressCaseDO stressCaseDO, ReportDTO reportDTO) {

        EnvironmentDO environmentDO = environmentMapper.selectById(stressCaseDO.getEnvironmentId());
        //创建引擎
        BaseStressEngine stressEngine = new StressSimpleEngine(environmentDO,stressCaseDO,reportDTO,applicationContext);

        //运行压测
        stressEngine.startStressTest();
    }
}
