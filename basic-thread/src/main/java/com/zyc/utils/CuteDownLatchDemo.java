package com.zyc.utils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author zyc
 * @date 2020/12/24
 * @time 23:52
 * @description : 计数器锁
 */
public class CuteDownLatchDemo {

    @Test
    public void threadComplute() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        new Thread(()->{

//            downLatch.countDown();
        }).start();

        new Thread(()->{


        }).start();

        downLatch.await();//这里阻塞等待减为0

        System.out.println("线程已经完成");
    }
}
