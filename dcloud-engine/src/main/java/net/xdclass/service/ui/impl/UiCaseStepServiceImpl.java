package net.xdclass.service.ui.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.mapper.UiCaseStepMapper;
import net.xdclass.model.UiCaseStepDO;
import net.xdclass.req.ui.UiCaseStepDelReq;
import net.xdclass.req.ui.UiCaseStepSaveReq;
import net.xdclass.req.ui.UiCaseStepUpdateReq;
import net.xdclass.service.ui.UiCaseStepService;
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
@Slf4j
public class UiCaseStepServiceImpl implements UiCaseStepService {

    @Resource
    private UiCaseStepMapper uiCaseStepMapper;


    @Override
    public Integer save(UiCaseStepSaveReq req) {
        UiCaseStepDO uiCaseStepDO = SpringBeanUtil.copyProperties(req, UiCaseStepDO.class);
        return uiCaseStepMapper.insert(uiCaseStepDO);
    }

    @Override
    public Integer update(UiCaseStepUpdateReq req) {
        UiCaseStepDO uiCaseStepDO = SpringBeanUtil.copyProperties(req, UiCaseStepDO.class);
        LambdaQueryWrapper<UiCaseStepDO> queryWrapper = new LambdaQueryWrapper<>(UiCaseStepDO.class);
        queryWrapper.eq(UiCaseStepDO::getId, uiCaseStepDO.getId()).eq(UiCaseStepDO::getProjectId, req.getProjectId());
        return uiCaseStepMapper.update(uiCaseStepDO, queryWrapper);
    }

    @Override
    public Integer delete(UiCaseStepDelReq req) {
        LambdaQueryWrapper<UiCaseStepDO> queryWrapper = new LambdaQueryWrapper<>(UiCaseStepDO.class);
        queryWrapper.eq(UiCaseStepDO::getId, req.getId()).eq(UiCaseStepDO::getProjectId, req.getProjectId());
        return uiCaseStepMapper.delete(queryWrapper);
    }
}
