package com.zyc.design02;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    //任务队列
    private ConcurrentLinkedQueue<Data> workQueue = new ConcurrentLinkedQueue<Data>();
    //worker集合
    private Map<String, Thread> workers = new HashMap<String, Thread>();
    //结果容器
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    public Master(Worker worker, int workerCount) {
        worker.setQueue(workQueue);
        worker.setResults(resultMap);

        for (int i = 0; i < workerCount; i++) {
            workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    public void submit(Data task) {
        workQueue.add(task);

    }

    public void execute() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public String getResult() {
        String priceResult = "RESULT ";
        for (Map.Entry<String, Object> me : resultMap.entrySet()) {
            priceResult += me.getValue();
        }
        return priceResult;
    }

}
