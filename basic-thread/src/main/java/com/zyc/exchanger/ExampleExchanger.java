package com.zyc.exchanger;

import java.util.concurrent.Exchanger;

/**
 * dsc: ExampleExchanger
 * date: 2020/11/26 15:12
 * author: zyc
 */
public class ExampleExchanger {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();


        new Thread(()->{
            try {
                System.out.println("t1线程交换");
                //拿不到数据是会阻塞的
                Object exchange = exchanger.exchange("Exchange --- " + Thread.currentThread().getName());
                System.out.println(exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                System.out.println("t2线程交换");
                Object exchange = exchanger.exchange("Exchange --- " + Thread.currentThread().getName());
                System.out.println(exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }

}
