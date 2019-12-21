package com.zyc.design02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Data> queue;
    private ConcurrentHashMap<String, Object> results;

    @Override
    public void run() {
        while (true) {
            Data data = queue.poll();
            if (data == null) break; //为null则任务处理结束

            String rs = handler(data);
            results.put(Integer.toString(data.getId()), rs);
        }
    }

    private String handler(Data data) {
        log.info("recive data [{}]", data.toString());

        try {
            log.info("bus use time 2 s");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this result";
    }


    public void setQueue(ConcurrentLinkedQueue<Data> queue) {
        this.queue = queue;
    }

    public void setResults(ConcurrentHashMap<String, Object> results) {
        this.results = results;
    }

}
