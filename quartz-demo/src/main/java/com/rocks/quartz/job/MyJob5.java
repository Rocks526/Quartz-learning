package com.rocks.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 通过JobDataMap获取数据
 * @author lizhaoxuan
 */
public class MyJob5 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 可以获取job和trigger的map
        Object msg = jobExecutionContext.get("msg");
        String msg1 = jobExecutionContext.getMergedJobDataMap().getString("msg");
        // 获取job的map
        String msg2 = jobExecutionContext.getJobDetail().getJobDataMap().getString("msg");
        String msg3 = jobExecutionContext.getTrigger().getJobDataMap().getString("msg");
        System.out.println(msg1);
        System.out.println(msg1);
        System.out.println(msg2);
        System.out.println(msg3);
    }
}
