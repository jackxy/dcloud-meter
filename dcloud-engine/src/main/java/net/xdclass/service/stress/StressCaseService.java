package net.xdclass.service.stress;

import net.xdclass.dto.stress.StressCaseDTO;
import net.xdclass.req.stress.StressCaseSaveReq;
import net.xdclass.req.stress.StressCaseUpdateReq;

public interface StressCaseService {
    StressCaseDTO findById(Long projectId, Long caseId);

    int delete(Long projectId, Long id);

    int save(StressCaseSaveReq req);

    int update(StressCaseUpdateReq req);

    void execute(Long projectId, Long caseId);
}
