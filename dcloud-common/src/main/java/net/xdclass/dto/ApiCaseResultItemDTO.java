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
public class ApiCaseResultItemDTO {
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
     * 请求头
     */
    private String requestHeader;

    /**
     * 请求体
     */
    private String requestBody;

    /**
     * 请求查询参数
     */
    private String requestQuery;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 响应体
     */
    private String responseBody;

    /**
     * ApiCaseStepDTO对象
     */
    private ApiCaseStepDTO apiCaseStep;


}
