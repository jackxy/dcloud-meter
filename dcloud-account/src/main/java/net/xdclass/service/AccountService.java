package net.xdclass.service;

import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;

import java.util.Map;

public interface AccountService {
    Map<String, Object> page(AccountPageReq req);

    int del(AccountDelReq req);

    int updateAccountStatus(AccountUpdateReq req);

    int register(AccountRegisterReq req);

    AccountDTO login(AccountLoginReq req);
}
