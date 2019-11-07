package com.zyc.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class PoolDemo {

    public static void main(String[] args) {
       ExecutorService executorService = Executors.newScheduledThreadPool(1);
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
}
