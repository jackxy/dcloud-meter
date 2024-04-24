package net.xdclass.service.ui;

import net.xdclass.req.ui.UiCaseStepDelReq;
import net.xdclass.req.ui.UiCaseStepSaveReq;
import net.xdclass.req.ui.UiCaseStepUpdateReq;

public interface UiCaseStepService {
    Integer save(UiCaseStepSaveReq req);

    Integer update(UiCaseStepUpdateReq req);

    Integer delete(UiCaseStepDelReq req);
}
