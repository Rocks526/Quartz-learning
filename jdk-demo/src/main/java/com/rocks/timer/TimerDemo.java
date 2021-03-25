package com.rocks.timer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer实现定时任务代码示例
 * @author lizhaoxuan
 */
@Slf4j
public class TimerDemo {

    private static final long delayTime = 3000L;

    private static final long intervalTime = 2000L;

    private static final long taskRunningTime = 3000L;

    public static void main(String[] args) {

        // 创建一个调度器 可以设置名字和是否设置为后台线程
        Timer timer = new Timer("Rocks",false);

        demo1(timer);
        demo2(timer);
        demo3(timer);
        demo4(timer);
        demo5(timer);
        demo6(timer);

    }


    /**
     * 延迟指定时间后周期执行
     *      如果任务执行时间小于周期间隔,则每个周期间隔执行一次
     *      如果任务执行时间大于周期间隔,会阻塞后续任务,当上个任务执行完后再开始执行
     *      但此方法会存在任务追赶,如果之前有任务被延迟执行了,当任务完成后,后续任务会追赶
     *          例如: 当前面有任务延迟后,下一个任务执行时间小于周期 schedule方法会周期执行 而fixedRate会追赶任务 任务完成后会立即执行 不等周期
     *      fixedRate的目的是在一段时间内执行指定次数
     * @param timer
     */
    @SneakyThrows
    private static void demo6(Timer timer) {
        // 延迟一段时间后开始周期性执行 之前的任务不会阻塞之后任务开始时间
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                long l = RandomUtils.nextLong(1000, taskRunningTime);
                log.info("task running,time:{},sleep:{}",new Date(),l);
                Thread.sleep(l);
            }
        },delayTime,intervalTime);
    }

    /**
     * 指定开始时间周期执行
     *      如果任务执行时间小于周期间隔,则每个周期间隔执行一次
     *      如果任务执行时间大于周期间隔,会阻塞后续任务,当上个任务执行完后再开始执行
     *      但此方法会存在任务追赶,如果之前有任务被延迟执行了,当任务完成后,后续任务会追赶
     *          例如: 当前面有任务延迟后,下一个任务执行时间小于周期 schedule方法会周期执行 而fixedRate会追赶任务 任务完成后会立即执行 不等周期
     *      fixedRate的目的是在一段时间内执行指定次数
     * @param timer
     */
    private static void demo5(Timer timer) {
        // 指定时间开始固定频率执行 之前的任务不会阻塞之后任务开始时间
        timer.scheduleAtFixedRate(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                long l = RandomUtils.nextLong(1000, taskRunningTime);
                log.info("task running,time:{},sleep:{}",new Date(),l);
                Thread.sleep(l);
            }
        },DateUtils.addSeconds(new Date(),5),intervalTime);
    }

    /**
     * 指定开始时间周期执行
     *      如果任务执行时间小于周期间隔,则每个周期间隔执行一次
     *      如果任务执行时间大于周期间隔,会阻塞后续任务,当上个任务执行完后再开始执行
     * @param timer
     */
    private static void demo4(Timer timer) {
        // 指定时间开始周期执行
        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                long l = RandomUtils.nextLong(1000, taskRunningTime);
                log.info("task running,time:{},sleep:{}",new Date(),l);
                Thread.sleep(l);
            }
            // 延迟5秒后 每2s执行一次
        },DateUtils.addSeconds(new Date(),5),intervalTime);
    }

    private static void demo3(Timer timer) {
        // 指定时间开始执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("task running!");
            }
            // 延迟五秒后执行
        },DateUtils.addSeconds(new Date(),5));
    }

    /**
     * 延迟一定时间后周期执行
     *      如果任务执行时间小于周期间隔,则每个周期间隔执行一次
     *      如果任务执行时间大于周期间隔,会阻塞后续任务,当上个任务执行完后再开始执行
     * @param timer
     */
    private static void demo2(Timer timer) {
        // 延迟指定时间后周期执行
        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                long l = RandomUtils.nextLong(1000, taskRunningTime);
                log.info("task running,time:{},sleep:{}",new Date(),l);
                Thread.sleep(l);
            }
        },delayTime,intervalTime);
    }

    private static void demo1(Timer timer) {
        // 延迟指定时间后执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("task running!");
            }
        }, delayTime);
    }

}
