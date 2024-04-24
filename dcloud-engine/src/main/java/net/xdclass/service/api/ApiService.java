package net.xdclass.service.api;

import net.xdclass.dto.api.ApiDTO;
import net.xdclass.req.api.ApiDelReq;
import net.xdclass.req.api.ApiSaveReq;
import net.xdclass.req.api.ApiUpdateReq;

public interface ApiService {

    ApiDTO getById(Long projectId, Long id);

    int save(ApiSaveReq req);

    int update(ApiUpdateReq req);

    int delete(ApiDelReq req);
}
