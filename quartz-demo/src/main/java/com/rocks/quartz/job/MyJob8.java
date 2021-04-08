package com.rocks.quartz.job;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.quartz.*;
import java.util.Date;

/**
 * 并发Job示例
 * @author lizhaoxuan
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyJob8 implements Job {

    @Getter@Setter
    private Integer count;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(Thread.currentThread() + ", count: " + count + " ,executed! Time: " + new Date());
        Thread.sleep(7000);
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count+1);
    }
}
