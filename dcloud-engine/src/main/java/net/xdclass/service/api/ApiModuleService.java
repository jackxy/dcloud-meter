package net.xdclass.service.api;

import net.xdclass.dto.api.ApiModuleDTO;
import net.xdclass.req.api.ApiModuleSaveReq;
import net.xdclass.req.api.ApiModuleUpdateReq;

import java.util.List;

public interface ApiModuleService {
    List<ApiModuleDTO> list(Long projectId);

    ApiModuleDTO getById(Long projectId, Long moduleId);

    int delete(Long id, Long projectId);

    int save(ApiModuleSaveReq req);

    int update(ApiModuleUpdateReq req);
}
