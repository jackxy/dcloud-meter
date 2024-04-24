package net.xdclass.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateReq {

    /**
     * 测试报告id
     */
    private Long id;

    /**
     * 执行状态
     */
    private String executeState;


    /**
     * 结束时间
     */
    private Long endTime;

}
