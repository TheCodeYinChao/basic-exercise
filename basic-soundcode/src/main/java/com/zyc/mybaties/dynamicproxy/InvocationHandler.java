package com.zyc.mybaties.dynamicproxy;

import java.lang.reflect.Method;

public class InvocationHandler implements java.lang.reflect.InvocationHandler {
    private Object target;

    public InvocationHandler(Object target) {
        this.target = target;
    }

    public  Object invoke(Object proxy, Method method, Object[]args) throws Throwable{
        System.out.println("invoke  method");
        System.out.println("method name " +method.getName());

        return method.invoke(target,args);

    }

}
