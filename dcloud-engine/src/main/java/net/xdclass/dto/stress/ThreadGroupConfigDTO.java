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
public class ThreadGroupConfigDTO {

    /**
     * 线程组名称
     */
    private String threadGroupName;

    /**
     * 线程数
     */
    private Integer numThreads;

    /**
     * 线程组启动时间
     */
    private Integer rampUp;

    /**
     * 循环次数，如果-1则是永久循环
     */
    private Integer loopCount;

    /**
     * 是否配置调度器
     */
    private Boolean schedulerEnabled;

    /**
     * 持续时间，秒
     */
    private Integer duration;

    /**
     * 启动延长时间，秒
     */
    private Integer delay;
}


