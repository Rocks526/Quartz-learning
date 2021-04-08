package com.rocks.quartz;

import com.rocks.quartz.job.SimpleJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.*;
import java.util.GregorianCalendar;


/**
 * SimpleTrigger示例
 * @author lizhaoxuan
 */
public class Demo9 {

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
                // 5S后开始执行
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                // 10min后结束执行 (和重复次数双重计算 只要有一个满足就结束)
                .endAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.MINUTE))
                // 设置Simple触发器
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 5s执行一次
                        .withIntervalInSeconds(5)
                        // 永久执行
                        .repeatForever()
                        // 设置重复次数
//                        .withRepeatCount(10)
                        // 设置misfire策略
                        .withMisfireHandlingInstructionFireNow())
                .build();

        // 4.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);

        // 5.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
