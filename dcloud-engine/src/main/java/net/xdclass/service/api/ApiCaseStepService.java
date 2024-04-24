package net.xdclass.service.api;

import net.xdclass.req.api.ApiCaseStepSaveReq;
import net.xdclass.req.api.ApiCaseStepUpdateReq;

public interface ApiCaseStepService {
    int save(ApiCaseStepSaveReq req);

    int update(ApiCaseStepUpdateReq req);

    int del(Long projectId, Long id);
}
