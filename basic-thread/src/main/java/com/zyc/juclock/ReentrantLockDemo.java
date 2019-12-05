package com.zyc.juclock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 关于aqs相关问题
 * https://www.cnblogs.com/waterystone/p/4920797.html
 * <p>
 * CANCELLED(1)：表示当前结点已取消调度。当timeout或被中断（响应中断的情况下），会触发变更为此状态，进入该状态后的结点将不会再变化。
 * <p>
 * SIGNAL(-1)：表示后继结点在等待当前结点唤醒。后继结点入队时，会将前继结点的状态更新为SIGNAL。
 * <p>
 * CONDITION(-2)：表示结点等待在Condition上，当其他线程调用了Condition的signal()方法后，CONDITION状态的结点将从等待队列转移到同步队列中，等待获取同步锁。
 * <p>
 * PROPAGATE(-3)：共享模式下，前继结点不仅会唤醒其后继结点，同时也可能会唤醒后继的后继结点。
 * <p>
 * 0：新结点入队时的默认状态。
 * <p>
 * 注意，负值表示结点处于有效等待状态，而正值表示结点已被取消。所以源码中很多地方用>0、<0来判断结点的状态是否正常。
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws Exception {

        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReentrantLock l = new ReentrantLock();
                l.lock();
            }
        }, "T2").start();
        reentrantLock.lock();

        reentrantLock.lockInterruptibly();

        reentrantLock.unlock();

        Condition condition = reentrantLock.newCondition();

        condition.await(); //阻塞

        condition.signal();//唤醒


        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();

        readLock.lock();
        readLock.unlock();
        ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();
        writeLock.lock();
        writeLock.unlock();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        countDownLatch.await();
        countDownLatch.countDown();

        Semaphore semaphore = new Semaphore(6);

        semaphore.acquire();
        semaphore.release();


        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        cyclicBarrier.await();
        cyclicBarrier.reset();
    }
}
