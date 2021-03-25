package com.rocks.timer;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过Thread配合while true实现定时任务
 * @author lizhaoxuan
 */
@Slf4j
public class ThreadDemo {

    private static final long INTERVAL_TIME = 3000L;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true){
                try {
                    // 任务逻辑
                    log.info("task running ....");
                    Thread.sleep(INTERVAL_TIME);
                } catch (Exception e) {
                    log.error("task running error,reason:{}",e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
