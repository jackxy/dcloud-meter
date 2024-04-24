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
public class ReportPageReq {

    private Long page;

    private Long size;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 用例id
     */
    private Long caseId;

    /**
     * 用例类型，API, UI STRESS
     */
    private String type;

    /**
     * 用例名称
     */
    private String name;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
