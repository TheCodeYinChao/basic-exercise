package com.zyc.juclock;

import java.util.concurrent.locks.ReentrantLock;

public class T1T2T3LOCK implements Runnable {
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        try {
            lock.lock();
//            Thread.sleep(10000);
            System.out.println("thread run ......");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        T1T2T3LOCK t1T2T3LOCK = new T1T2T3LOCK();
        new Thread(t1T2T3LOCK, "T1").start();
//        new Thread(t1T2T3LOCK,"T2").start();
//        new Thread(t1T2T3LOCK,"T3").start();

    }
}
