package net.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("net.xdclass.mapper")
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {

         SpringApplication.run(GatewayApplication.class, args);
    }
}
