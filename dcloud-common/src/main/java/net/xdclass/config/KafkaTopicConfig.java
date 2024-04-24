package net.xdclass.config;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class KafkaTopicConfig {

    /**
     * 压测
     */
    public static final String STRESS_TOPIC_NAME = "stress_report_topic1";

    /**
     * 接口自动化
     */
    public static final String API_TOPIC_NAME = "api_report_topic2";

    /**
     * ui自动化
     */
    public static final String UI_TOPIC_NAME = "ui_report_topic333";

    /**
     * 报告状态的topic
     */
    public static final String REPORT_STATE_TOPIC_NAME = "report_state_topic";
}
