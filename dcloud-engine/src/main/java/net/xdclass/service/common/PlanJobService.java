package net.xdclass.service.common;

import net.xdclass.dto.common.PlanJobDTO;
import net.xdclass.req.common.PlanJobDelReq;
import net.xdclass.req.common.PlanJobPageReq;
import net.xdclass.req.common.PlanJobSaveReq;
import net.xdclass.req.common.PlanJobUpdateReq;

import java.util.List;
import java.util.Map;

public interface PlanJobService {
    int save(PlanJobSaveReq req);

    int update(PlanJobUpdateReq req);

    int del(PlanJobDelReq req);

    Map<String, Object> page(PlanJobPageReq req);

    List<PlanJobDTO> listAvailableJobs();

    void processJobs();

}
