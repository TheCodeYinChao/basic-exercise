package com.zyc.mybaties.intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TargetProxy implements InvocationHandler {
    private Object target;

    private List<Intercept> list;

    public TargetProxy(Object target) {
        this.target = target;
        this.list =new ArrayList<>();
    }

    public  static Object waper(Object object,TargetProxy target) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),target);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target,method,args);
        System.out.println("代理类");
        this.list.forEach(x->
                x.interce(invocation)
        );

        return method.invoke(target,args);
    }

    public  void addInteceper(Intercept intercept){
        this.list.add(intercept);
    }
}
