package net.xdclass.service.ui;

import net.xdclass.dto.dto.UiCaseModuleDTO;
import net.xdclass.req.ui.UiCaseModuleSaveReq;
import net.xdclass.req.ui.UiCaseModuleUpdateReq;

import java.util.List;

public interface UiCaseModuleService {
    List<UiCaseModuleDTO> list(Long projectId);

    UiCaseModuleDTO getById(Long projectId, Long moduleId);

    Integer save(UiCaseModuleSaveReq req);

    Integer update(UiCaseModuleUpdateReq req);

    Integer delete(Long projectId, Long id);
}
