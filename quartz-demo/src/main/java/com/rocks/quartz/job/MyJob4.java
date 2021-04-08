package com.rocks.quartz.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * JobDataMap传递数据
 * @author lizhaoxuan
 */
public class MyJob4 implements Job {

    /**
     * 通过get/set方法获取jobDataMap里的数据
     * 在实例化时,会自动调用set map的key必须和属性名称一致
     */
    @Getter@Setter
    private String msg;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("msg: " + msg);
    }
}
