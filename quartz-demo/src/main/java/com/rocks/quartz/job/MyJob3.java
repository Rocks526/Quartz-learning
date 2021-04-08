package com.rocks.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * JobDetail无参构造函数验证
 * @author lizhaoxuan
 */
public class MyJob3 implements Job {

    public MyJob3(String msg){
        System.out.println("msg....");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job execute, Time: " + new Date());
    }
}
