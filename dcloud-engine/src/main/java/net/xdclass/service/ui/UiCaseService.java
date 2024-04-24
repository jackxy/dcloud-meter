package net.xdclass.service.ui;

import net.xdclass.dto.dto.UiCaseDTO;
import net.xdclass.req.ui.UiCaseDelReq;
import net.xdclass.req.ui.UiCaseSaveReq;
import net.xdclass.req.ui.UiCaseUpdateReq;
import net.xdclass.util.JsonData;

public interface UiCaseService {
    UiCaseDTO find(Long projectId, Long caseId);

    Integer delete(UiCaseDelReq req);

    Integer update(UiCaseUpdateReq req);

    Integer save(UiCaseSaveReq req);

    JsonData execute(Long projectId, Long caseId);
}
