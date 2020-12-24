package com.zyc.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zyc
 * @date 2020/12/25
 * @time 0:22
 * @description :
 */
public class SemapphoreDemo {
    private static ExecutorService executor = Executors.newFixedThreadPool(30);
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        // 获取一个"许可证"
                        semaphore.acquire();

                        // 模拟数据保存
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("save date...");

                        // 执行完后,归还"许可证"
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}
