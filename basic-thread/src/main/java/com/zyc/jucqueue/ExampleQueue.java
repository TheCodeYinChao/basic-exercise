package com.zyc.jucqueue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * dsc: ExampleQueue
 * date: 2020/11/26 16:05
 * author: zyc
 */
public class ExampleQueue {
    @Test
    public void baseUser() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        queue.add(1);
//        queue.add(2);//满 抛出异常
        queue.offer("2");//满 返回false

    /*    for(int i = 0;i<4;i++){
            Object peek1 = queue.element();
            System.out.println(peek1);
            Object peek = queue.peek();
            System.out.println(peek);
        }*/

        for(int i = 0;i<4;i++){
//            Object peek1 = queue.remove();
//            System.out.println(peek1);
            Object peek = queue.poll();//这个是不阻塞的
            System.out.println(peek);
        }

    }



}
