package com.zyc.design01.bak;

import org.springframework.util.StringUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zyc
 * @date 2020/11/16
 * @time 22:29
 * @description :
 */
public class FetureData implements Data {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String rs;
    private RealData data;

    public FetureData(RealData data) {
        this.data = data;
    }

    @Override
    public void request(String arg) {
        try {
            lock.lock();
            data.request(arg);
            rs = data.getResult().toString();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object getResult() {
        if(StringUtils.isEmpty(rs)){
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        return rs;
    }
}
