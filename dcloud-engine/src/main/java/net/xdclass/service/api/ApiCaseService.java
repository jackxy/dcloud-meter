package net.xdclass.service.api;


import net.xdclass.dto.api.ApiCaseDTO;
import net.xdclass.req.api.ApiCaseDelReq;
import net.xdclass.req.api.ApiCaseSaveReq;
import net.xdclass.req.api.ApiCaseUpdateReq;
import net.xdclass.util.JsonData;

public interface ApiCaseService {
    ApiCaseDTO getById(Long projectId, Long id);

    int save(ApiCaseSaveReq req);

    int update(ApiCaseUpdateReq req);

    int del(Long projectId,Long id);

    JsonData execute(Long projectId, Long caseId);
}
