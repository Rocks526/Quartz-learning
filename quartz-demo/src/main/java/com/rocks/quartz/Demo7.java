package com.rocks.quartz;

import com.rocks.quartz.job.MyJob8;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Trigger公共属性示例
 * @author lizhaoxuan
 */
public class Demo7 {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        // 1.创建scheduler && 启动
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // 2.创建JobDetail
        JobDetail jobDetail = JobBuilder
                // 指定任务
                .newJob(MyJob8.class)
                // 指定任务实例名称和组
                .withIdentity("myJob1", "MyGroup1")
                .build();

        // 3.创建触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                // 被调度后立即开始执行
                .startNow()
                // 开始时间
                .startAt(new Date())
                // 停止时间
                .endAt(DateUtils.addDays(new Date(),7))
                // triggerKey
                .withIdentity(new TriggerKey("trigger1","trigger"))
                // 设置优先级
                .withPriority(10)
                // 设置Simple触发器
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 5s执行一次
                        .withIntervalInSeconds(5)
                        // 永久执行
                        .repeatForever()
                        // 设置misfire策略
                        .withMisfireHandlingInstructionFireNow())
                .build();

        // 3.创建触发器
        CronTrigger trigger2 = TriggerBuilder.newTrigger()
                // 被调度后立即开始执行
                .startNow()
                // 开始时间
                .startAt(new Date())
                // 停止时间
                .endAt(DateUtils.addDays(new Date(),7))
                // triggerKey
                .withIdentity(new TriggerKey("trigger2","trigger"))
                // 设置优先级
                .withPriority(-1)
                // 设置Simple触发器
                .withSchedule(CronScheduleBuilder
                        // cron表达式
                        .cronSchedule("* * * * * *")
                        // misfire机制
                        .withMisfireHandlingInstructionDoNothing())
                .build();

        // 4.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(jobDetail,trigger2);

        // 5.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
