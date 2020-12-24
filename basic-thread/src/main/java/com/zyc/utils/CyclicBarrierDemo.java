package com.zyc.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author zyc
 * @date 2020/12/25
 * @time 0:18
 * @description : 格栅屏障锁
 */
public class CyclicBarrierDemo {

    @Test
    public void demo() throws IOException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        Executor executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    System.out.println("条件前");
                    cyclicBarrier.await();
                    System.out.println("条件后");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
        
        
        System.in.read();

    }
    /**
     * 条件前
     条件前
     条件前
     条件前
     条件后
     条件后
     条件后
     条件后
     */
}
