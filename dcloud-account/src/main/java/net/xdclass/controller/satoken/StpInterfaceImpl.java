//package net.xdclass.controller.satoken;
//
//import cn.dev33.satoken.stp.StpInterface;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
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
//@Component
//public class StpInterfaceImpl implements StpInterface {
//    //private AccountMapper accountMapper;
//
//    @Override
//    public List<String> getPermissionList(Object loginId, String loginType) {
//
//        System.out.println("触发权限查询方法");
//        List<String> list = new ArrayList<>();
//        list.add("user.add");
//        list.add("user.update");
//        list.add("user.get");
//        list.add("art.*");
//        return list;
//    }
//
//    @Override
//    public List<String> getRoleList(Object loginId, String loginType) {
//        System.out.println("触发角色查询方法");
//        List<String> list = new ArrayList<>();
//        list.add("admin");
//        list.add("super-admin");
//        list.add("super.*");
//        return list;
//    }
//}
