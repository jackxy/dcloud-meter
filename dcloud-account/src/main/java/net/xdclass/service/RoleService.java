package net.xdclass.service;

import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;
import net.xdclass.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    int addPermission(RoleAddPermissionReq req);

    int delPermission(RoleDelPermissionReq req);

    int addRoleByAccountId(AccountRoleAddReq req);

    int delRoleByAccountId(AccountRoleDelReq req);

    List<RoleDTO> list();

    int addRole(RoleAddReq addReq);

    int deleteRole(Long id);

    AccountDTO getAccountWithRoleByAccountId(Long accountId);
}
