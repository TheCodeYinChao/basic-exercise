package com.zyc.juclock;

import java.util.concurrent.locks.LockSupport;

/**
 * park  这阻塞 线程中断会唤醒park
 * 以及 unpark 指定线程也会唤醒park
 */
public class PackDemo {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("阻塞后");
                System.out.println(Thread.interrupted());
            }
        });
        thread.start();

//        thread.interrupt();
        Thread.sleep(2000);
        LockSupport.unpark(thread);
    }

}
