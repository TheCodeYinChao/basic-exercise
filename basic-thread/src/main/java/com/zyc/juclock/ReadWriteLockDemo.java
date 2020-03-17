package com.zyc.juclock;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁：读读互斥  读写互斥 写写互斥
 *
 * 读写锁 读读共享 读写互斥 写写互斥
 *
 */
public class ReadWriteLockDemo {
    private static  ReentrantReadWriteLock lock = new  ReentrantReadWriteLock();

    
    public static void main(String[] args) {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        //读锁
        readLock.lock();
        readLock.unlock();

        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //写锁
        writeLock.lock();
        writeLock.unlock();

//        锁升级 ： 读锁未释放申请写锁 会导致死锁
        //锁降级  那么锁降级有什么用？答案是为了可见性的保证
//       完整代码：update（是否更新的标志, false 标识可以更新，volatile修饰，使所有线程能够实时知道数据是否要改变）。
//
//importmy java.sql.Time;
//importmy java.util.concurrent.TimeUnit;
//importmy java.util.concurrent.locks.ReentrantReadWriteLock;
//
///**
// * 锁降级的学习
// */
//        public class LockDegradation implements Runnable{
//            /**
//             * 共享资源
//             */
//            public static int index;
//            /**
//             * 是否更新的标志, false 标识可以更新
//             */
//            public static volatile boolean update;
//            private ReentrantReadWriteLock reentrantReadWriteLock;
//
//            public LockDegradation() {
//                update = false;
//                reentrantReadWriteLock = new ReentrantReadWriteLock();
//            }
//
//            public void setUpdate(boolean update) {
//                LockDegradation.update = update;
//            }
//
//            @Override
//            public void run() {
//                while (true) {
//                    work();
//                }
//            }
//
//            public void work() {
//                reentrantReadWriteLock.readLock().lock();
//
//                if (!update) {
//                    reentrantReadWriteLock.readLock().unlock();
//
//                    // 锁降级开始
//                    reentrantReadWriteLock.writeLock().lock();
//                    try {
//                        if (!update) {
//                            // 准备数据
//                            ++index;
//                            System.out.println("【数据更新】");
//                            try {
//                                TimeUnit.MILLISECONDS.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            update = true;
//                        }
//                        reentrantReadWriteLock.readLock().lock();
//                    } finally {
//                        reentrantReadWriteLock.writeLock().unlock();
//                        // 锁降级结束,降级为读锁
//                    }
//                }
//                try {
//                    // 使用数据
//                    for (int i=0; i<5; i++) {
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getName() + ":" + index);
//                    }
//                } finally {
//                    reentrantReadWriteLock.readLock().unlock();
//                }
//            }
//
//            public static void main(String[] args) {
//                LockDegradation lockDegradation = new LockDegradation();
//                Thread thread1 = new Thread(lockDegradation);
//                Thread thread2 = new Thread(lockDegradation);
//                Thread thread3 = new Thread(lockDegradation);
//                thread1.start();
//                thread2.start();
//                thread3.start();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                LockDegradation.update = false;
//                try {
//                    TimeUnit.MILLISECONDS.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                LockDegradation.update = false;
//            }
//        }
    }

}
