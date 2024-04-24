package net.xdclass.service.common.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.common.ProjectDTO;
import net.xdclass.mapper.ProjectMapper;
import net.xdclass.model.ProjectDO;
import net.xdclass.req.common.ProjectSaveReq;
import net.xdclass.req.common.ProjectUpdateReq;
import net.xdclass.service.common.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {


    @Resource
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectDTO> list() {
        List<ProjectDO> projectDOS = projectMapper.selectList(null);
        return SpringBeanUtil.copyProperties(projectDOS, ProjectDTO.class);
    }

    @Override
    public int save(ProjectSaveReq projectSaveReq) {
        ProjectDO projectDO = SpringBeanUtil.copyProperties(projectSaveReq, ProjectDO.class);
        return projectMapper.insert(projectDO);
    }

    @Override
    public int update(ProjectUpdateReq projectUpdateReq) {

        ProjectDO projectDO = SpringBeanUtil.copyProperties(projectUpdateReq, ProjectDO.class);
        return projectMapper.updateById(projectDO);
    }

    @Override
    public int delete(Long id) {
        return projectMapper.deleteById(id);
    }
}
