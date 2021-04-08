package com.rocks.quartz.job;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

/**
 * Job实现类
 * @author lizhaoxuan
 */
public class SimpleJob implements Job {

    private final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 任务执行
        System.out.println("Job executed, Time: " + DateFormatUtils.format(new Date(),DATE_FORMAT_PATTERN));
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
