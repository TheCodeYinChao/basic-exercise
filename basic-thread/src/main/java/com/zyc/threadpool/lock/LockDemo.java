package com.zyc.threadpool.lock;

import sun.rmi.runtime.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2019/11/20.
 */
public class LockDemo {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();;
        lock.unlock();
    }
}
