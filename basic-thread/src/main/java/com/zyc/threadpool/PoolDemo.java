package com.zyc.threadpool;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class PoolDemo {


    /**
     * 关于线程池中如何复用 已经各参数之间的关系
     * 精髓连接地址  https://blog.csdn.net/anhenzhufeng/article/details/88870374
     * <p>
     * 线程池博大精深--对于用好多线程有很多重要的启迪作用
     * <p>
     * 同时找到一个有关jdk源码探索的方法
     *
     *
     * 线程池也会有自己的相关状态
     *   private static final int RUNNING    = -1 << COUNT_BITS; //线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理
     *   private static final int SHUTDOWN   =  0 << COUNT_BITS;//线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。 调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。
     *   private static final int STOP       =  1 << COUNT_BITS;//线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。 调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。
     *   private static final int TIDYING    =  2 << COUNT_BITS;//当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现   当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING
     *   private static final int TERMINATED =  3 << COUNT_BITS;//线程池彻底终止，就变成TERMINATED状态。  线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。
     *
     * @param args
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(1);

        Executors.newCachedThreadPool();
        Executors.newCachedThreadPool();
        Future<?> submit = executorService.submit(new Runnable() {
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

    public void test() {
        ExecutorService executorService = new ThreadPoolExecutor(1,
                3,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300), new ThreadFactoryImpl(), new RejectedExecutionHandlerImpl());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    class ThreadFactoryImpl implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup threadGroup = new ThreadGroup("12");
            Thread thread = new Thread(threadGroup, "工作线程");
            return thread;
        }
    }

    class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
        Map map = new HashMap();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            map.put("1", r);
        }
    }

    static class test {
        public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                }
            }).start();
        }
    }

    @Test
    public void tests1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> submit = executorService.submit(() -> {
            System.out.println("执行数据！！");
        });
        submit.get();

    }
}
