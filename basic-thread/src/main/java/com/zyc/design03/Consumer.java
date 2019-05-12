package com.zyc.design03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class Consumer implements Runnable {
    private BlockingQueue<Data> queue;

    private volatile boolean isStop = false;


    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isStop){
            Data data = queue.poll();
            if(data == null){continue;}
            try {
                log.info("recive data [{}]",data.toString());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isStop = true;
    }
}
