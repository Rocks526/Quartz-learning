package com.rocks.timer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer并发任务问题
 * @author lizhaoxuan
 */
@Slf4j
public class TimerCurrentDemo {

    public static void main(String[] args) {

        // 由于Timer是单线程调度 当某个任务执行超时后 会导致别的任务也延迟执行
        // 如果任务逻辑放到子线程异步处理 又会很耗费线程资源
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("task1 run,time:{}",new Date());
                Thread.sleep(3000);
            }
        },0,2000);
        // 任务1的延迟会导致任务2也每3s执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("task2 run,time:{}",new Date());
            }
        },0,2000);
    }

}
