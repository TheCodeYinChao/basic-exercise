package com.zyc.threadpool;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class PoolDemo {


    /**
     * 关于线程池中如何复用 已经各参数之间的关系
     * 精髓连接地址  https://blog.csdn.net/anhenzhufeng/article/details/88870374
     *
     * 线程池博大精深--对于用好多线程有很多重要的启迪作用
     *
     * 同时找到一个有关jdk源码探索的方法
     * @param args
     */

    public static void main(String[] args) {
       ExecutorService executorService = Executors.newScheduledThreadPool(1);

        Executors.newCachedThreadPool();
        Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        executorService.shutdown();

        //线程异常处理
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void test(){
        ExecutorService executorService = new ThreadPoolExecutor(1,
        3,
        30,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(300),new ThreadFactoryImpl(),new RejectedExecutionHandlerImpl());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    class ThreadFactoryImpl implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup threadGroup = new ThreadGroup("12");
            Thread thread = new Thread(threadGroup,"工作线程");
            return thread;
        }
    }

    class RejectedExecutionHandlerImpl implements  RejectedExecutionHandler{
        Map map = new HashMap();
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            map.put("1",r);
        }
    }

   static class test{
        public static void main(String[] args) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(1);
                    }
                }).start();
        }
    }
}
