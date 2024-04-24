package net.xdclass.controller.req;

import lombok.Data;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
public class AccountRegisterReq {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 账号唯一标识
     */
    private String identifier;

    /**
     * 凭证，密码
     */
    private String credential;

    /**
     * 账号类型，手机，邮箱，微信，支付宝等
     */
    private String identityType;
}
