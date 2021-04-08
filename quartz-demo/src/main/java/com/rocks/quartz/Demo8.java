package com.rocks.quartz;

import com.rocks.quartz.job.SimpleJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.*;

import java.util.GregorianCalendar;


/**
 * Calendar示例
 * @author lizhaoxuan
 */
public class Demo8 {

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

        // 3.创建Calendar
        // 排除一年中的某些天
        AnnualCalendar annualCalendar = new AnnualCalendar();
        // GregorianCalendar是用来生成Date的类 虽然设置了年分 但annualCalendar只会取月和日
        annualCalendar.setDayExcluded(new GregorianCalendar(2021, 4,8),true);
        // Cron表达式排除早晚 只在早8-晚5执行
        CronCalendar cronCalendar = new CronCalendar("* * 0-7,18-23 ? * *");
        // 排除一天中的某些时间不执行
        DailyCalendar dailyCalendar = new DailyCalendar("20:57:00", "20:59:00");
        dailyCalendar.setInvertTimeRange(true);
        // 排除某些节假日 用法和AnnualCalendar一致 但年份有效
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        holidayCalendar.addExcludedDate(new GregorianCalendar(2021,4,8).getTime());
        // 排除每个月中的某些天
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar();
        monthlyCalendar.setDayExcluded(1,true);
        monthlyCalendar.setDayExcluded(7,true);
        // 排除星期中的某一天
        WeeklyCalendar weeklyCalendar = new WeeklyCalendar();
        weeklyCalendar.setDayExcluded(java.util.Calendar.THURSDAY, true);

        // 4.将Calendar注册给Schedule  此处示例只注册一个
        scheduler.addCalendar("calendar1",monthlyCalendar,false,false);

        // 5.创建触发器 绑定Calendar
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                // 被调度后立即开始执行
                .startNow()
                // 设置Calendar
                .modifiedByCalendar("calendar1")
                // 设置Simple触发器
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 5s执行一次
                        .withIntervalInSeconds(5)
                        // 永久执行
                        .repeatForever()
                        // 设置misfire策略
                        .withMisfireHandlingInstructionFireNow())
                .build();

        // 6.将Job和Trigger注册给Scheduler, 任务开始调度
        scheduler.scheduleJob(jobDetail,trigger);

        // 7.休眠一段时间后停止scheduler
        Thread.sleep(10*60*1000);
        scheduler.shutdown();
    }

}
