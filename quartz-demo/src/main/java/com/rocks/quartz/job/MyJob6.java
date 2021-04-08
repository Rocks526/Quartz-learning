package com.rocks.quartz.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Job状态持久化测试
 * @author lizhaoxuan
 */
public class MyJob6 implements Job {

    @Setter@Getter
    private Integer count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("count: " + count);
        // 更新count
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count+1);
    }
}
