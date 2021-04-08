package com.rocks.quartz;

import com.rocks.quartz.job.SimpleJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * CronTrigger的misfire示例
 * @author lizhaoxuan
 */
public class Demo12 {

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
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                // 设置Cron触发器
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                                //.withMisfireHandlingInstructionDoNothing()
                                .withMisfireHandlingInstructionFireAndProceed()
                                //.withMisfireHandlingInstructionIgnoreMisfires()
                                )
                .build();


        // 4.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);

        // 5.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
