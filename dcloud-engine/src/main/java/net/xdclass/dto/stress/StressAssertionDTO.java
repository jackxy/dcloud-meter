package net.xdclass.dto.stress;

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
public class StressAssertionDTO {

    /**
     * 断言名称
     */
    private String name;

    /**
     * 断言规则，"contain|equal"
     */
    private String action;

    /**
     * 断言字段类型， "responseCode|responseData|responseHeader"
     */
    private String from;

    /**
     * 断言目标值
     */
    private String value;
}
