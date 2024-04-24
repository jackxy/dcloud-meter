package net.xdclass.service.api;

import net.xdclass.dto.api.ApiCaseDTO;
import net.xdclass.dto.api.ApiCaseModuleDTO;
import net.xdclass.req.api.ApiCaseModuleDelReq;
import net.xdclass.req.api.ApiCaseModuleSaveReq;
import net.xdclass.req.api.ApiCaseModuleUpdateReq;

import java.util.List;

public interface ApiCaseModuleService {

    List<ApiCaseModuleDTO> list(Long projectId);

    ApiCaseModuleDTO getById(Long projectId, Long moduleId);

    int save(ApiCaseModuleSaveReq req);

    int update(ApiCaseModuleUpdateReq req);

    int del(ApiCaseModuleDelReq req);
}
