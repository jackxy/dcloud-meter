package net.xdclass.service.stress.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.stress.StressCaseDTO;
import net.xdclass.dto.stress.StressCaseModuleDTO;
import net.xdclass.mapper.StressCaseMapper;
import net.xdclass.mapper.StressCaseModuleMapper;
import net.xdclass.model.StressCaseDO;
import net.xdclass.model.StressCaseModuleDO;
import net.xdclass.req.stress.StressCaseModuleSaveReq;
import net.xdclass.req.stress.StressCaseModuleUpdateReq;
import net.xdclass.service.stress.StressCaseModuleService;
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
public class StressCaseModuleServiceImpl implements StressCaseModuleService {

    @Resource
    private StressCaseModuleMapper caseModuleMapper;

    @Resource
    private StressCaseMapper stressCaseMapper;

    @Override
    public List<StressCaseModuleDTO> list(Long projectId) {

        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId, projectId);
        List<StressCaseModuleDO> stressCaseModuleDOS = caseModuleMapper.selectList(queryWrapper);
        List<StressCaseModuleDTO> list = SpringBeanUtil.copyProperties(stressCaseModuleDOS, StressCaseModuleDTO.class);
        list.forEach(source -> {
            //查询压测模型下的关联用例
            LambdaQueryWrapper<StressCaseDO> caseQueryWrapper = new LambdaQueryWrapper<>();
            caseQueryWrapper.eq(StressCaseDO::getModuleId, source.getId()).orderByDesc(StressCaseDO::getId);

            List<StressCaseDO> stressCaseDOS = stressCaseMapper.selectList(caseQueryWrapper);
            List<StressCaseDTO> stressCaseDTOS = SpringBeanUtil.copyProperties(stressCaseDOS, StressCaseDTO.class);
            source.setList(stressCaseDTOS);
        });
        return list;
    }

    @Override
    public StressCaseModuleDTO findById(Long projectId, Long moduleId) {
        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId, projectId).eq(StressCaseModuleDO::getId, moduleId);
        StressCaseModuleDO stressCaseModuleDO = caseModuleMapper.selectOne(queryWrapper);
        if (stressCaseModuleDO == null) {
            return null;
        }
        StressCaseModuleDTO stressCaseModuleDTO = SpringBeanUtil.copyProperties(stressCaseModuleDO, StressCaseModuleDTO.class);

        //查询压测模型下的关联用例
        LambdaQueryWrapper<StressCaseDO> caseQueryWrapper = new LambdaQueryWrapper<>();
        caseQueryWrapper.eq(StressCaseDO::getModuleId, moduleId).orderByDesc(StressCaseDO::getId);
        List<StressCaseDO> stressCaseDOS = stressCaseMapper.selectList(caseQueryWrapper);
        List<StressCaseDTO> stressCaseDTOS = SpringBeanUtil.copyProperties(stressCaseDOS, StressCaseDTO.class);
        stressCaseModuleDTO.setList(stressCaseDTOS);
        return stressCaseModuleDTO;
    }

    @Override
    public int delete(Long projectId, Long id) {
        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId, projectId).eq(StressCaseModuleDO::getId, id);
        int delete = caseModuleMapper.delete(queryWrapper);
        //删除模块下的关联用例
        if (delete > 0) {
            LambdaQueryWrapper<StressCaseDO> caseQueryWrapper = new LambdaQueryWrapper<>();
            caseQueryWrapper.eq(StressCaseDO::getModuleId, id);
            stressCaseMapper.delete(caseQueryWrapper);
        }
        return delete;
    }

    @Override
    public int save(StressCaseModuleSaveReq req) {

        StressCaseModuleDO stressCaseModuleDO = SpringBeanUtil.copyProperties(req, StressCaseModuleDO.class);
        return caseModuleMapper.insert(stressCaseModuleDO);
    }

    @Override
    public int update(StressCaseModuleUpdateReq req) {
        StressCaseModuleDO stressCaseModuleDO = SpringBeanUtil.copyProperties(req, StressCaseModuleDO.class);
        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId, stressCaseModuleDO.getProjectId()).eq(StressCaseModuleDO::getId, stressCaseModuleDO.getId());
        return caseModuleMapper.update(stressCaseModuleDO, queryWrapper);
    }
}
