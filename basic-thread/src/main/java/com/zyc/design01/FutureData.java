package com.zyc.design01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2019/5/12.
 * 这里用到锁，会导致阻塞
 */
public class FutureData implements Data {
    private RealData realData;

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private volatile boolean isComplement = false;


    public void setRealData(RealData realData) {
        this.realData = realData;

    }

    @Override
    public void request() {
        try {
            lock.lock();
            realData.request();
            isComplement = true;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getResult() {
        try {
            lock.lock();
            if (!isComplement) {
                condition.await();//结果为组装好
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return realData.getResult();
    }
}
