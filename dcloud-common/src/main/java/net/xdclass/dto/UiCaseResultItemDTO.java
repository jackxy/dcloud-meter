package net.xdclass.dto;

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
public class UiCaseResultItemDTO {
    /**
     * 报告ID
     */
    private Long reportId;

    /**
     * 执行状态
     */
    private Boolean executeState;

    /**
     * 断言状态
     */
    private Boolean assertionState;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * 耗时
     */
    private Long expendTime;

    /**
     * 截图地址
     */
    private String screenshotUrl;

    /**
     * 用例步骤
     */
    private UiCaseStepDTO uiCaseStep;

}
