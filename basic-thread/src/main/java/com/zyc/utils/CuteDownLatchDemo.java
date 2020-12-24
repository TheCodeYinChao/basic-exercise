package com.zyc.utils;

import org.junit.Test;
import sun.java2d.SurfaceDataProxy;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zyc
 * @date 2020/12/24
 * @time 23:52
 * @description : 计数器锁
 */
public class CuteDownLatchDemo {

    @Test
    public void threadComplute() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(2);
        new Thread(()->{
            System.out.println("任务进行中1 ----》");
            downLatch.countDown();
        }).start();

        new Thread(()->{
            System.out.println("任务进行中2 ----》");
            downLatch.countDown();
        }).start();

        downLatch.await();//这里阻塞等待减为0

        System.out.println("线程已经完成");
    }

    /**
     * 模拟一个并发访问工具
     */
    @Test
    public void concurrence()throws Exception{
        Executor executor =
                new ThreadPoolExecutor(50,50,2, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1000));

        CountDownLatch downLatch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    System.out.println("ssss");
                    downLatch.await();//阻塞等待计数器减到1释放
                    Random r = new Random();
                    int i1 = r.nextInt(5000);
                    TimeUnit.MICROSECONDS.sleep(i1);
                    System.out.println("Request   ..." +i1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            });
        }
        downLatch.countDown();//减一，批量执行request

        System.in.read();

    }
}
