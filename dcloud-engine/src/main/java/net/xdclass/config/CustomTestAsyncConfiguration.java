package net.xdclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Configuration
public class CustomTestAsyncConfiguration {

    @Bean("testExecutor")
    public Executor stressTestExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池的核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);
        // 设置线程池的最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(20);
        // 缓冲队列：用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        // 设置线程池中除了核心线程之外的线程的最长存活时间, 允许线程的空闲时间60秒：当超过了空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置线程池中所有线程的名字前缀：设置好了之后可以方便定位处理任务所在的线程池
        executor.setThreadNamePrefix("xdclass-test-");
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }

}
