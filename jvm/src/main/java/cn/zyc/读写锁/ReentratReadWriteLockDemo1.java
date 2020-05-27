package cn.zyc.读写锁;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author qhy
 * @description
 * @createTime: 2020/5/27
 */
public class ReentratReadWriteLockDemo1 {

    private int i = 0;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    void lockDegrade() {
        writeLock.lock();
        try {
            i++;
//            readLock.lock();//未释放写锁之前获取读锁
        } finally {
            writeLock.unlock();
        }
        //Do something
        try {
            System.out.println(Thread.currentThread().getName() + " i:" + i);
        } finally {
//            readLock.unlock();
        }
    }

    public static void main(String[] a) {
        ReentratReadWriteLockDemo1 demo3 = new ReentratReadWriteLockDemo1();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                demo3.lockDegrade();
            }, "线程"+i).start();
        }
    }

}

