package com.rocks.quartz;

import com.rocks.quartz.job.MyJob4;
import com.rocks.quartz.job.MyJob5;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 通过JobDataMap传递数据
 * @author lizhaoxuan
 */
public class Demo4 {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        // 1.创建scheduler && 启动
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // 2.创建JobDetail
        JobDetail jobDetail = JobBuilder
                // 指定任务
                .newJob(MyJob5.class)
                // 通过JobDataMap传递数据
                .usingJobData("msg","send msg by jobDataMap!")
                // 指定任务实例名称和组
                .withIdentity("myJob1", "MyGroup1")
                .build();

        // 3.创建触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                // 被调度后立即开始执行
                .startNow()
                // trigger也有jobDataMap
                .usingJobData("msg","send msg by trigger jobDataMap!")
                // 设置触发器类型
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 5s执行一次
                        .withIntervalInSeconds(5)
                        // 永久执行
                        .repeatForever())
                .build();

        // 4.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);

        // 5.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
