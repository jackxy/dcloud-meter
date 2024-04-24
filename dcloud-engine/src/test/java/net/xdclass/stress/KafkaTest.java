package net.xdclass.stress;

import jakarta.annotation.Resource;
import net.xdclass.config.KafkaTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@SpringBootTest
public class KafkaTest {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    @Test
    public void testSendMsg(){
        kafkaTemplate.send(KafkaTopicConfig.STRESS_TOPIC_NAME,"case_id_"+1,"test 6666 8888 xdclass");
    }

}

