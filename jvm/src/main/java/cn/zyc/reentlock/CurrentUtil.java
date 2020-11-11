package cn.zyc.reentlock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 2020/8/20.
 * 并发工具类
 */
public class CurrentUtil {
    @Test
    public void countDownLatch() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        executorService.submit(()->{
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务-》1");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务-》2");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务-》3");
            countDownLatch.countDown();
        });
        executorService.submit(()->{
            System.out.println("任务-》4");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        countDownLatch.await();

        System.out.println("aa");
    }
}
