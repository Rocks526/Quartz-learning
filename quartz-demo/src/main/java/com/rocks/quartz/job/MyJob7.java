package com.rocks.quartz.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 实现Job持久化 保留状态
 * @author lizhaoxuan
 */
@PersistJobDataAfterExecution
public class MyJob7 implements Job {

    @Getter@Setter
    private Integer count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("count: " +count);
        // 更新
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count+1);
    }
}
