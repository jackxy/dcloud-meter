//package net.xdclass.controller.satoken;
//
//import cn.dev33.satoken.stp.SaTokenInfo;
//import cn.dev33.satoken.stp.StpUtil;
//import net.xdclass.util.JsonData;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * 小滴课堂,愿景：让技术不再难学
// *
// * @Description
// * @Author 二当家小D
// * @Remark 有问题直接联系我，源码-笔记-技术交流群
// * @Version 1.0
// **/
//@RestController
//public class TestController {
//    // 会话登录接口,多次登录返回token相同，配置文件那边配置
//    @RequestMapping("login")
//    public JsonData doLogin(String name, String pwd) {
//        // 第1步：比对前端提交的账号名称、密码
//        if("laowang".equals(name) && "123456".equals(pwd)) {
//
//            // 第2步：根据账号id，进行登录
//            //检查此账号是否之前已有登录；
//            //为账号生成 Token 凭证与 Session 会话；
//            //记录 Token 活跃时间；
//            //通知全局侦听器，xx 账号登录成功；
//            // 将 Token 注入到请求上下文；
//            StpUtil.login(10001);
//
//            // 第3步，获取 Token  相关参数, StpUtil.login(id) 方法利用了 Cookie 自动注入的特性，省略手写返回 token 的代码
//            // 可以手动获取相关Token信息返回
//            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//            return JsonData.buildSuccess(tokenInfo);
//        }
//        return JsonData.buildError("登录失败");
//    }
//
//    @RequestMapping("logout")
//    public JsonData doLogout() {
//        // 第1步：根据当前登录账号，进行登出
//        //删除 Token 活跃时间；
//        //通知全局侦听器，xx 账号登出成功；
//        // 将 Token 注销掉；
//        StpUtil.logout();
//        return JsonData.buildSuccess();
//    }
//
//    @RequestMapping("isLogin")
//    public JsonData isLogin() {
//        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
//        boolean login = StpUtil.isLogin();
//        if(login){
//            Object loginId = StpUtil.getLoginId();
//            System.out.println("当前登录账号：" + loginId);
//        }
//        return JsonData.buildSuccess(login);
//    }
//
//
//
//
//    /**
//     * 获取当前账号的权限列表
//     * @return 返回一个包含当前账号所有权限的列表
//     */
//    @RequestMapping("getPermissionList")
//    public JsonData getPermissionList() {
//        // 获取当前账号的权限列表
//        List<String> permissionList = StpUtil.getPermissionList();
//        return JsonData.buildSuccess(permissionList);
//    }
//
//    /**
//     * 判断当前账号是否具有指定权限
//     * @return 如果当前账号具有指定权限，则返回true，否则返回false
//     */
//    @RequestMapping("hasPermission")
//    public JsonData hasPermission() {
//        // 判断当前账号是否含有指定权限
//        boolean hasPermission = StpUtil.hasPermission("user.add");
//        return JsonData.buildSuccess(hasPermission);
//    }
//
//    /**
//     * 校验当前账号是否具有指定权限，不通过则抛出异常
//     * 无返回值，若权限校验不通过则抛出NotPermissionException异常
//     */
//    @RequestMapping("checkPermission")
//    public JsonData checkPermission() {
//        // 校验当前账号是否含有指定权限，不通过则抛出异常
//        StpUtil.checkPermission("user.add");
//        return JsonData.buildSuccess();
//    }
//
//    /**
//     * 校验当前账号是否同时具有多个指定权限，全部通过才返回成功
//     * 无返回值，若权限校验不通过则抛出NotPermissionException异常
//     */
//    @RequestMapping("checkPermissionAnd")
//    public JsonData checkPermissionAnd() {
//        // 校验当前账号是否同时具有多个指定权限
//        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");
//        return JsonData.buildSuccess();
//    }
//
//    /**
//     * 校验当前账号是否至少具有多个指定权限之一，通过一个即可返回成功
//     * 无返回值，若权限校验不通过则抛出NotPermissionException异常
//     */
//    @RequestMapping("checkPermissionOr")
//    public JsonData checkPermissionOr() {
//        // 校验当前账号是否至少具有多个指定权限之一
//        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");
//        return JsonData.buildSuccess();
//    }
//
//
//
//
//
//    /**
//     * 获取当前账号所拥有的角色集合
//     * @return 返回一个包含当前账号角色的列表
//     */
//    @RequestMapping("getRoleList")
//    public JsonData getRoleList() {
//        // 获取账号角色列表
//        List<String> roleList = StpUtil.getRoleList();
//        return JsonData.buildSuccess(roleList);
//    }
//
//    /**
//     * 判断当前账号是否拥有指定角色
//     * @return 如果账号拥有指定角色返回true，否则返回false
//     */
//    @RequestMapping("hasRole")
//    public JsonData hasRole() {
//        // 判断是否拥有指定角色
//        boolean hasRole = StpUtil.hasRole("老王超级管理员");
//        return JsonData.buildSuccess(hasRole);
//    }
//
//    /**
//     * 校验当前账号是否含有指定角色标识
//     * 如果账号不含有指定角色，会抛出异常
//     */
//    @RequestMapping("checkRole")
//    public JsonData checkRole() {
//        // 校验是否含有指定角色
//        StpUtil.checkRole("super-admin");
//        return JsonData.buildSuccess();
//    }
//
//    /**
//     * 校验当前账号是否同时含有多个指定角色标识
//     * 如果账号不含有所有指定角色，会抛出异常
//     */
//    @RequestMapping("checkRoleAnd")
//    public JsonData checkRoleAnd() {
//        // 校验是否同时含有多个指定角色
//        StpUtil.checkRoleAnd("super-admin", "shop-admin");
//        return JsonData.buildSuccess();
//    }
//
//    /**
//     * 校验当前账号是否至少含有一个指定角色标识
//     * 如果账号不含有任何指定角色，会抛出异常
//     */
//    @RequestMapping("checkRoleOr")
//    public JsonData checkRoleOr() {
//        // 校验是否至少含有一个指定角色
//        StpUtil.checkRoleOr("super-admin", "shop-admin");
//        return JsonData.buildSuccess();
//    }
//
//
//
//
//
//}
