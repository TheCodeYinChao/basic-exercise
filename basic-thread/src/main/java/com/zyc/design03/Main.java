package com.zyc.design03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 2019/5/12.
 */
public class Main {
    public static void main(String[] args) {
        BlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(10);
        ExecutorService ex = Executors.newFixedThreadPool(3);
        ExecutorService exc = Executors.newFixedThreadPool(3);

        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);


        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        ex.execute(p1);
        ex.execute(p2);
        ex.execute(p3);
        exc.execute(c1);
        exc.execute(c2);
        exc.execute(c3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.stop();
        p2.stop();
        p3.stop();
        ex.shutdown();
    }
}
