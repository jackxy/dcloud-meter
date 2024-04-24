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
public class StressSampleResultDTO {

    /**
     *结果ID
     */
    private Long reportId;

    /**
     * 时间戳
     */
    private Long sampleTime;
    /**
     *采样器标签，请求名称
     */
    private String samplerLabel;
    /**
     *采样次数
     */
    private Long samplerCount;
    /**
     *平均响应时间
     */
    private Double meanTime;
    /**
     *最小响应时间
     */
    private Integer minTime;
    /**
     *最大响应时间
     */
    private Integer maxTime;

    /**
     *错误百分比
     */
    private Double errorPercentage;

    /**
     * 错误请求数
     */
    private Long errorCount;
    /**
     *每秒请求速率
     */
    private Double requestRate;
    /**
     *每秒接收KB
     */
    private Double receiveKBPerSecond;
    /**
     *每秒发送KB
     */
    private Double sentKBPerSecond;

    /**
     *线程数量
     */
    private Integer threadCount;

    /**
     * 请求协议 主机 路径 端口  参数
     */
    private String requestLocation;
    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 请求参数
     */
    private String requestBody;
    /**
     * 响应码
     */
    private String responseCode;
    /**
     * 响应头
     */
    private String responseHeader;
    /**
     * 响应体
     */
    private String responseData;

    /**
     * 断言信息
     */
    private String assertInfo;
}
