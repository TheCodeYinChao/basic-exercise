package com.zyc.mybaties.intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TargetProxy1 implements InvocationHandler {
    private Object target;

    private Intercept list;

    public TargetProxy1(Object target,Intercept intercept) {
        this.target = target;
        this.list = intercept;
    }

    public  static Object waper(Object object,Intercept target) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),new TargetProxy1(object,target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target,method,args);
        System.out.println("代理类");
        return list.interce(invocation);
    }

}
