package com.zyc.juclock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockDemo {

    public static void main(String[] args)throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReentrantLock l = new ReentrantLock();
                l.lock();
            }
        },"T2").start();
        reentrantLock.lock();

        reentrantLock.lockInterruptibly();

        reentrantLock.unlock();

        Condition condition = reentrantLock.newCondition();

        condition.await(); //阻塞

        condition.signal();//唤醒

//        CountDownLatch  countDownLatch = new CountDownLatch(1);
//
//        Semaphore semaphore = new Semaphore(6);
//
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
//
//        ReentrantReadWriteLock  rw = new ReentrantReadWriteLock();
//        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
//        ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();
    }
}
