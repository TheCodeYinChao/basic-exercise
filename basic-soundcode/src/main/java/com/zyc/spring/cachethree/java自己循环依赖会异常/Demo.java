package com.zyc.spring.cachethree.java自己循环依赖会异常;

/**
 * dsc: Demo
 * date: 2021/1/11 15:44
 * author: zyc
 */
public class Demo {
    public static void main(String[] args) {
        new A();


//        Exception in thread "main" java.lang.StackOverflowError
    }

   static class A {
        A(){
            new B();
        }
    }

    static  class B{
        B(){
            new A();
        }
    }
}
