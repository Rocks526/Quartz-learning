package com.rocks.quartz;

import com.rocks.quartz.job.SimpleJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * SimpleTrigger的misfire机制
 * @author lizhaoxuan
 */
public class Demo10 {

    @SneakyThrows
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        // 1.创建scheduler && 启动
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // 2.创建JobDetail
        JobDetail jobDetail = JobBuilder
                // 指定任务
                .newJob(SimpleJob.class)
                // 指定任务实例名称和组
                .withIdentity("myJob1", "MyGroup1")
                .build();

        // 3.创建触发器 绑定Calendar
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .startAt(DateBuilder.dateOf(14,0,0))
                // 设置Simple触发器
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 5s执行一次
                        .withIntervalInSeconds(5)
                        // 永久执行
                        .withRepeatCount(3)
                        // .withMisfireHandlingInstructionFireNow()
                        // .withMisfireHandlingInstructionIgnoreMisfires()
                        // .withMisfireHandlingInstructionNextWithExistingCount()
                        // .withMisfireHandlingInstructionNextWithRemainingCount()
                        // .withMisfireHandlingInstructionNowWithExistingCount()
                         .withMisfireHandlingInstructionNowWithRemainingCount()
                        )
                .build();

        // 4.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);

        // 5.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
