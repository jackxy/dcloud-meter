package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.KafkaTopicConfig;
import net.xdclass.controller.req.ReportDelReq;
import net.xdclass.controller.req.ReportExportReq;
import net.xdclass.controller.req.ReportPageReq;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.ReportExcelDTO;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.mapper.ReportDetailApiMapper;
import net.xdclass.mapper.ReportDetailStressMapper;
import net.xdclass.mapper.ReportDetailUiMapper;
import net.xdclass.mapper.ReportMapper;
import net.xdclass.model.ReportDO;
import net.xdclass.model.ReportDetailApiDO;
import net.xdclass.model.ReportDetailStressDO;
import net.xdclass.model.ReportDetailUiDO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.ReportService;
import net.xdclass.util.JsonUtil;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private ReportDetailStressMapper reportDetailStressMapper;

    @Resource
    private ReportDetailUiMapper reportDetailUiMapper;

    @Resource
    private ReportDetailApiMapper reportDetailApiMapper;

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public ReportDTO save(ReportSaveReq req) {

        ReportDO reportDO = SpringBeanUtil.copyProperties(req, ReportDO.class);
        reportMapper.insert(reportDO);

        ReportDTO reportDTO = ReportDTO.builder().id(reportDO.getId())
                .projectId(reportDO.getProjectId())
                .name(reportDO.getName()).build();

        return reportDTO;
    }

    @Override
    public void updateReportState(ReportUpdateReq req) {

        ReportDTO reportDTO = ReportDTO.builder().id(req.getId()).executeState(req.getExecuteState()).endTime(req.getEndTime()).build();

        ReportDO reportDO = reportMapper.selectById(reportDTO.getId());
        //查找数据库测试报告明细
        LambdaQueryWrapper<ReportDetailStressDO> queryWrapper = new LambdaQueryWrapper<>(ReportDetailStressDO.class);
        queryWrapper.eq(ReportDetailStressDO::getReportId, reportDTO.getId());
        queryWrapper.orderByDesc(ReportDetailStressDO::getSamplerCount).last("limit 1");

        ReportDetailStressDO oldReportDetailStressDO = reportDetailStressMapper.selectOne(queryWrapper);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("超时等待错误");
            reportDO.setExecuteState(ReportStateEnum.EXECUTE_FAIL.name());
        }
        ReportDetailStressDO newReportDetailStressDO = reportDetailStressMapper.selectOne(queryWrapper);

        if(newReportDetailStressDO.getSamplerCount() > oldReportDetailStressDO.getSamplerCount()){
            //有新数据，则重新发送MQ消息
            reportDO.setExecuteState(ReportStateEnum.COUNTING_REPORT.name());
            kafkaTemplate.send(KafkaTopicConfig.REPORT_STATE_TOPIC_NAME,"report_id_"+reportDTO.getId(), JsonUtil.obj2Json(req));
        }else {
            //没更新，则处理完成测试报告
            reportDO.setExecuteState(ReportStateEnum.EXECUTE_SUCCESS.name());
        }
        //处理聚合统计信息
        reportDO.setEndTime(reportDTO.getEndTime());
        reportDO.setExpandTime(reportDTO.getEndTime()-reportDO.getStartTime());
        reportDO.setQuantity(newReportDetailStressDO.getSamplerCount());
        reportDO.setFailQuantity(newReportDetailStressDO.getErrorCount());
        reportDO.setPassQuantity(reportDO.getQuantity()-reportDO.getFailQuantity());

        Map<String,Object> summmaryMap = new HashMap<>();
        summmaryMap.put("QPS",newReportDetailStressDO.getRequestRate());
        summmaryMap.put("错误请求百分比",newReportDetailStressDO.getErrorPercentage());
        summmaryMap.put("平均响应时间(毫秒)",newReportDetailStressDO.getMeanTime());
        summmaryMap.put("最大响应时间(毫秒)",newReportDetailStressDO.getMaxTime());
        summmaryMap.put("最小响应时间(毫秒)",newReportDetailStressDO.getMinTime());

        reportDO.setSummary(JsonUtil.obj2Json(summmaryMap));

        //更新测试报告
        reportMapper.updateById(reportDO);
    }

    @Override
    public List<ReportExcelDTO> exportReport(ReportExportReq req) {
        LambdaQueryWrapper<ReportDO> queryWrapper = new LambdaQueryWrapper<>(ReportDO.class);
        //构建查询条件
        if (req.getProjectId() != null){
            queryWrapper.eq(ReportDO::getProjectId,req.getProjectId());
        }
        if (req.getCaseId() != null){
            queryWrapper.eq(ReportDO::getCaseId,req.getCaseId());
        }
        if (req.getType() != null){
            queryWrapper.eq(ReportDO::getType,req.getType());
        }
        if (req.getName() != null){
            queryWrapper.like(ReportDO::getName,req.getName());
        }
        if (req.getStartTime() != null){
            queryWrapper.ge(ReportDO::getStartTime,req.getStartTime());
        }
        if (req.getEndTime() != null){
            queryWrapper.le(ReportDO::getEndTime,req.getEndTime());
        }
        queryWrapper.orderByDesc(ReportDO::getId);
        List<ReportDO> reportDOS = reportMapper.selectList(queryWrapper);
        List<ReportExcelDTO> reportExcelDTOS = SpringBeanUtil.copyProperties(reportDOS, ReportExcelDTO.class);
        return reportExcelDTOS;
    }

    @Override
    public Map<String, Object> page(ReportPageReq req) {

        LambdaQueryWrapper<ReportDO> queryWrapper = new LambdaQueryWrapper<>(ReportDO.class);
        //构建查询条件
        if (req.getProjectId() != null){
            queryWrapper.eq(ReportDO::getProjectId,req.getProjectId());
        }
        if (req.getCaseId() != null){
            queryWrapper.eq(ReportDO::getCaseId,req.getCaseId());
        }
        if (req.getType() != null){
            queryWrapper.eq(ReportDO::getType,req.getType());
        }
        if (req.getName() != null){
            queryWrapper.like(ReportDO::getName,req.getName());
        }
        if (req.getStartTime() != null){
            queryWrapper.ge(ReportDO::getStartTime,req.getStartTime());
        }
        if (req.getEndTime() != null){
            queryWrapper.le(ReportDO::getEndTime,req.getEndTime());
        }
        queryWrapper.orderByDesc(ReportDO::getId);
        Page<ReportDO> page = new Page<>(req.getPage(),req.getSize());
        Page<ReportDO> reportDOPage = reportMapper.selectPage(page, queryWrapper);
        //获取测试报告列表
        List<ReportDO> reportDOS = reportDOPage.getRecords();
        List<ReportDTO> reportDTOList = SpringBeanUtil.copyProperties(reportDOS, ReportDTO.class);
        Map<String,Object> pageMap = new HashMap<>(3);
        pageMap.put("total_record",reportDOPage.getTotal());
        pageMap.put("total_page",reportDOPage.getPages());
        pageMap.put("current_data",reportDTOList);

        return pageMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(ReportDelReq req) {

        TestTypeEnum testTypeEnum = TestTypeEnum.valueOf(req.getType());
        LambdaQueryWrapper<ReportDO> queryWrapper = new LambdaQueryWrapper<>(ReportDO.class);
        queryWrapper.eq(ReportDO::getProjectId,req.getProjectId());
        queryWrapper.eq(ReportDO::getId,req.getId());
        int delete = reportMapper.delete(queryWrapper);
        //根据不同的类型删除明细表
        switch (testTypeEnum){
            case STRESS:
                reportDetailStressMapper.delete(new LambdaQueryWrapper<ReportDetailStressDO>().eq(ReportDetailStressDO::getReportId,req.getId()));
                break;
            case API:
                reportDetailApiMapper.delete(new LambdaQueryWrapper<ReportDetailApiDO>().eq(ReportDetailApiDO::getReportId,req.getId()));
                break;
            case UI:
                reportDetailUiMapper.delete(new LambdaQueryWrapper<ReportDetailUiDO>().eq(ReportDetailUiDO::getReportId,req.getId()));
                break;
            default:
                log.error("不支持的类型");
                break;
        }
        return delete;
    }
}
