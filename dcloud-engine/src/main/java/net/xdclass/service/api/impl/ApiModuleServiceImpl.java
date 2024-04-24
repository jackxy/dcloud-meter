package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.dto.api.ApiDTO;
import net.xdclass.dto.api.ApiModuleDTO;
import net.xdclass.mapper.ApiMapper;
import net.xdclass.mapper.ApiModuleMapper;
import net.xdclass.model.ApiDO;
import net.xdclass.model.ApiModuleDO;
import net.xdclass.req.api.ApiModuleSaveReq;
import net.xdclass.req.api.ApiModuleUpdateReq;
import net.xdclass.service.api.ApiModuleService;
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
public class ApiModuleServiceImpl implements ApiModuleService {

    @Resource
    private ApiModuleMapper apiModuleMapper;

    @Resource
    private ApiMapper apiMapper;


    /**
     * 根据项目ID获取ApiModuleDTO列表
     *
     * @param projectId 项目ID
     * @return ApiModuleDTO列表
     */
    @Override
    public List<ApiModuleDTO> list(Long projectId) {

        // 创建查询封装对象
        LambdaQueryWrapper<ApiModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiModuleDO::getProjectId, projectId);

        // 查询ApiModuleDO列表
        List<ApiModuleDO> apiModuleDOS = apiModuleMapper.selectList(queryWrapper);

        // 将ApiModuleDO列表转换为ApiModuleDTO列表
        List<ApiModuleDTO> list = SpringBeanUtil.copyProperties(apiModuleDOS, ApiModuleDTO.class);

        // 遍历ApiModuleDTO列表
        list.forEach(apiModuleDTO -> {
            // 创建查询封装对象
            LambdaQueryWrapper<ApiDO> apiQueryWrapper = new LambdaQueryWrapper<>();
            apiQueryWrapper.eq(ApiDO::getModuleId, apiModuleDTO.getId()).orderByDesc(ApiDO::getId);

            // 查询ApiDO列表
            List<ApiDO> apiDOS = apiMapper.selectList(apiQueryWrapper);

            // 将ApiDO列表转换为ApiDTO列表，并设置给ApiModuleDTO的list属性
            apiModuleDTO.setList(SpringBeanUtil.copyProperties(apiDOS, ApiDTO.class));

        });

        // 返回ApiModuleDTO列表
        return list;
    }

    @Override
    public ApiModuleDTO getById(Long projectId, Long moduleId) {
        LambdaQueryWrapper<ApiModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiModuleDO::getProjectId, projectId).eq(ApiModuleDO::getId, moduleId);

        ApiModuleDO apiModuleDO = apiModuleMapper.selectOne(queryWrapper);

        ApiModuleDTO apiModuleDTO = SpringBeanUtil.copyProperties(apiModuleDO, ApiModuleDTO.class);

        //查询模块下面对关联接口
        if (apiModuleDTO != null) {

            LambdaQueryWrapper<ApiDO> apiQueryWrapper = new LambdaQueryWrapper<>();

            apiQueryWrapper.eq(ApiDO::getModuleId, apiModuleDTO.getId()).orderByDesc(ApiDO::getId);

            List<ApiDO> apiDOS = apiMapper.selectList(apiQueryWrapper);

            apiModuleDTO.setList(SpringBeanUtil.copyProperties(apiDOS, ApiDTO.class));
        }
        return apiModuleDTO;
    }

    /**
     * 根据projectId和moduleId删除用例模块
     *
     * @param id
     * @param projectId
     * @return
     */
    @Override
    public int delete(Long id, Long projectId) {

        LambdaQueryWrapper<ApiModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiModuleDO::getProjectId, projectId).eq(ApiModuleDO::getId, id);
        int deleted = apiModuleMapper.delete(queryWrapper);
        if (deleted != 0) {
            //删除模块下面的api
            LambdaQueryWrapper<ApiDO> apiQueryWrapper = new LambdaQueryWrapper<>();
            apiQueryWrapper.eq(ApiDO::getModuleId, id).eq(ApiDO::getProjectId, projectId);
            apiMapper.delete(apiQueryWrapper);
        }
        return deleted;

    }

    /**
     * 保存模块对象
     *
     * @param req
     * @return
     */
    @Override
    public int save(ApiModuleSaveReq req) {

        ApiModuleDO apiModuleDO = SpringBeanUtil.copyProperties(req, ApiModuleDO.class);
        return apiModuleMapper.insert(apiModuleDO);

    }

    /**
     * 更新
     *
     * @param req
     * @return
     */
    @Override
    public int update(ApiModuleUpdateReq req) {
        ApiModuleDO apiModuleDO = SpringBeanUtil.copyProperties(req, ApiModuleDO.class);
        LambdaQueryWrapper<ApiModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiModuleDO::getId, apiModuleDO.getId()).eq(ApiModuleDO::getProjectId, apiModuleDO.getProjectId());
        return apiModuleMapper.update(apiModuleDO, queryWrapper);
    }

}
