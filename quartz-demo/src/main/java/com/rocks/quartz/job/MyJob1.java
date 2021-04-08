package com.rocks.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * JobDetail实例化验证
 * @author lizhaoxuan
 */
public class MyJob1 implements Job {

    public MyJob1(){
        System.out.println("new MyJob1...");
    }

    private int i = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        i++;
        System.out.println("i: " + i);
    }
}
