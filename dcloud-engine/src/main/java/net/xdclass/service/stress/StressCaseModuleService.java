package net.xdclass.service.stress;

import net.xdclass.dto.stress.StressCaseModuleDTO;
import net.xdclass.req.stress.StressCaseModuleSaveReq;
import net.xdclass.req.stress.StressCaseModuleUpdateReq;

import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public interface StressCaseModuleService {

    List<StressCaseModuleDTO> list(Long projectId);

    StressCaseModuleDTO findById(Long projectId, Long moduleId);

    int delete(Long projectId, Long id);

    int save(StressCaseModuleSaveReq req);

    int update(StressCaseModuleUpdateReq req);
}
