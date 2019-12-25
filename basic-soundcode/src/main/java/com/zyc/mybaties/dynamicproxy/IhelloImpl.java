package com.zyc.mybaties.dynamicproxy;

public class IhelloImpl implements Ihello {

    @Override
    public void sayHello() {
        System.out.println("hello world ");
    }
}
