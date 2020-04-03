package com.zyc.juclock;

/**
 * 死循环会占用cpu 导致cpu飚高 这个跟cpu核数有关
 *
 * 可以通过相关方法 sleep（有待商榷） yield wait await park 让出cpu 的执行权
 */
public class LoopDead {
    public static void main(String[] args) throws Exception {

        while (true){
//            Thread.sleep(1000);
//            Thread.yield();
//            Object.wait();
//            await
//            park
        }



//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){}
//            }
//        }).start();
//
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){}
//            }
//        }).start();
//



    }
}
