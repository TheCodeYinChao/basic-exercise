package com.zyc.threadinterrupt;

public class ThreadDemo {
    public static void main(String[] args) {
        Thread.interrupted();//作用于当前线程  是检测中断并清除中断状态


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (Thread.currentThread().isInterrupted()) {//检测中断位置
                        System.out.println("线程已经中断");

                        break;
                    }
                }

            }
        });
        thread.start();
        thread.interrupt(); //线程设置中断位置

    }
}
