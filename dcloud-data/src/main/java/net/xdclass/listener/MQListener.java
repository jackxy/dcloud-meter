package net.xdclass.listener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.KafkaTopicConfig;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.ReportDetailService;
import net.xdclass.service.ReportService;
import net.xdclass.util.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
@Slf4j
public class MQListener {

    @Resource
    private ReportDetailService reportDetailService;


    @Resource
    private ReportService reportService;

    /**
     * 消费监听，压测日志详情
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.STRESS_TOPIC_NAME},groupId = "xdclass-stress-test-gp")
    public void onStressReportDetailMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        reportDetailService.handleStressReportDetail(record.value().toString());
        ack.acknowledge();
    }



    /**
     * 消费监听，接口自动化测试日志详情
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.API_TOPIC_NAME},groupId = "xdclass-api-test-gp")
    public void onApiReportDetailMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        reportDetailService.handleApiReportDetail(record.value().toString());
        ack.acknowledge();
    }




    /**
     * 消费监听，UI自动化测试日志详情
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.UI_TOPIC_NAME},groupId = "xdclass-ui-test-gp")
    public void onUiReportDetailMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        reportDetailService.handleUiReportDetail(record.value().toString());
        ack.acknowledge();
    }






    /**
     * 消费监听，处理报告的状态
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.REPORT_STATE_TOPIC_NAME},groupId = "xdclass-report-test-gp")
    public void onStressReportStateMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        ReportUpdateReq reportUpdateReq = JsonUtil.json2Obj(record.value().toString(), ReportUpdateReq.class);
        reportService.updateReportState(reportUpdateReq);
        ack.acknowledge();
    }

}
