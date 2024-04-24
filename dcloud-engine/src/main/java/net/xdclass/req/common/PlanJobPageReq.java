package net.xdclass.req.common;

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
public class PlanJobPageReq {

    private Long page;

    private Long size;

    private Long projectId;

    private String name;

}
