package net.xdclass.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;
import net.xdclass.service.AccountService;
import net.xdclass.service.RoleService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private RoleService roleService;

    /**
     * 分页查看账号列表
     */
    @PostMapping("page")
    public JsonData page(@RequestBody AccountPageReq req) {
        Map<String, Object> pageInfo = accountService.page(req);
        return JsonData.buildSuccess(pageInfo);
    }


    /**
     * 根据id删除
     */
    @PostMapping("del")
    public JsonData delete(@RequestBody AccountDelReq req) {
        int rows = accountService.del(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 更新账号状态
     *
     * @param req
     * @return
     */
    @PostMapping("update")
    public JsonData updateAccountStatus(@RequestBody AccountUpdateReq req) {

        int rows = accountService.updateAccountStatus(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 注册接口
     */
    @PostMapping("register")
    public JsonData register(@RequestBody AccountRegisterReq req) {
        int rows = accountService.register(req);
        return JsonData.buildSuccess(rows);
    }


    /**
     * 登录接口
     * JWT
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody AccountLoginReq req) {

        AccountDTO accountDTO = accountService.login(req);
        if (accountDTO != null) {
            //登录
            StpUtil.login(accountDTO.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return JsonData.buildSuccess(tokenInfo);
        } else {
            return JsonData.buildError("登录失败");
        }
    }


    /**
     * 退出登录接口
     */
    @GetMapping("logout")
    public JsonData logout() {
        StpUtil.logout();
        return JsonData.buildSuccess();
    }


    /**
     * 根据登录账号获取角色信息
     * @return
     */
    @GetMapping("findLoginAccountRole")
    public JsonData findLoginAccountRole(){
        Long accountId = Long.parseLong(StpUtil.getLoginId().toString());
        AccountDTO accountDTO = roleService.getAccountWithRoleByAccountId(accountId);
        return JsonData.buildSuccess(accountDTO);
    }



}
