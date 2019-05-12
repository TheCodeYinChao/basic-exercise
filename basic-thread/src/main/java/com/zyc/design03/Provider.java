package com.zyc.design03;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 2019/5/12.
 */
public class Provider implements Runnable{
    private BlockingQueue<Data> queue;
    //多线程间是否启动变量，有强制从主内存中刷新的功能。即时返回线程的状态
    private volatile boolean isRunning = true;

    private static AtomicInteger count = new AtomicInteger();

    private static Random r = new Random();

    public Provider(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(isRunning){
            try {
                //随机休眠0 - 1000 毫秒 表示获取数据(产生数据的耗时)
                Thread.sleep(r.nextInt(1000));
                //获取的数据进行累计...
                int id = count.incrementAndGet();
                System.out.println(id);
                //比如通过一个getData方法获取了
                Data data = new Data();
                data.setId(id);
                data.setName("zyc");
                System.out.println("当前线程:" + Thread.currentThread().getName() + ", 获取了数据，id为:" + id + ", 进行装载到公共缓冲区中...");
                boolean isOffSuccess = false;
                while (!isOffSuccess){
                    isOffSuccess = commitTask(data);
                }
            }catch (Exception e){

            }
        }
    }

    public boolean commitTask(Data data)throws Exception{
        System.out.println("提交缓冲区数据失败时需要重试 ....");
        return queue.offer(data,2,TimeUnit.SECONDS);
    }

    public void stop(){
        this.isRunning = false;
    }
}
