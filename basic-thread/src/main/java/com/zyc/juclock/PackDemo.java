package com.zyc.juclock;

import java.util.concurrent.locks.LockSupport;

public class PackDemo {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("阻塞后");
                System.out.println(Thread.interrupted());
            }
        });
        thread.start();

        thread.interrupt();
//        Thread.sleep(2000);
//        LockSupport.unpark(thread);
    }

}
