package com.rocks.timer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService代码示例
 * @author lizhaoxuan
 */
@Slf4j
public class ScheduledExecutorDemo {

    private static final long delayTime = 3000L;

    private static final long intervalTime = 2000L;

    private static final long taskRunningTime = 3000L;

    public static void main(String[] args) {
        // 创建线程池调度器
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
//         ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 提交任务 可以提交runnable callable
//        demo1(scheduledExecutorService);
//        demo2(scheduledExecutorService);
        demo3(scheduledExecutorService);
    }

    /**
     * 并发任务测试
     *      多线程: task1每两秒执行一次 task2的阻塞不会影响到其他任务
     *      单线程: 和Timer一样存在并发问题, 某个任务的阻塞会影响到其他并发任务
     * @param scheduledExecutorService
     */
    private static void demo3(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.scheduleAtFixedRate(() -> log.info("task1 running!"),delayTime, intervalTime, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                log.info("task2 running!");
                Thread.sleep(taskRunningTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },delayTime,intervalTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 延迟一定时间后周期执行 效果和Timer的fixedRate一致
     * @param scheduledExecutorService
     */
    private static void demo2(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long l = RandomUtils.nextLong(1000, taskRunningTime);
            log.info("task running,time:{},sleep:{}",new Date(),l);
            try {
                Thread.sleep(l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },delayTime,intervalTime,TimeUnit.MILLISECONDS);
    }

    /**
     * 延迟指定时间后执行一次
     * @param scheduledExecutorService
     */
    private static void demo1(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.schedule(() -> log.info("task running!"),delayTime, TimeUnit.MILLISECONDS);
    }

}
