package net.xdclass.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.common.PlanJobService;
import org.redisson.api.RLock;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.redisson.api.RedissonClient;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@EnableScheduling
@Configuration
@Slf4j
public class TestPlanJob {

    @Resource
    private PlanJobService planJobService;


    @Resource
    private RedissonClient redissonClient;

    private static final String LOCK_KEY_NAME = "plan_job:key";



    /**
     * 每分钟执行1次定时调度
     */
    @Scheduled(cron = "0 * * * * ?")
    public void start(){

        RLock lock = redissonClient.getLock(LOCK_KEY_NAME);
        try {
            lock.lock();
            planJobService.processJobs();
        }catch (Exception e){
            log.error("定时任务执行异常",e);
        }finally {
            lock.unlock();
        }

    }

}
