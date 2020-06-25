package cn.zyc;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Admin on 2020/5/23.
 */
public class Demo {
    public LinkedList linkedList = new LinkedList();

    public synchronized  void push(Object object){
        synchronized (linkedList){
            linkedList.addLast(object);
            notify();
        }
    }

    public synchronized  Object pop()throws Exception{
        synchronized (linkedList){
            if(linkedList.size() <= 0){
                wait();
            }
            return linkedList.removeLast();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i =100;
                    while (i>=0){
                        demo.push("a");
                        i--;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i =0 ;i<50 ;i++){
                    System.out.println(demo.pop());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i =0 ;i<50 ;i++){
                        System.out.println(demo.pop());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();




    }
}
