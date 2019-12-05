package com.zyc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class Invocation implements InvocationHandler {
    private Object object;

    public Invocation() {

    }

    public Object setAgent(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(Invocation.class.getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("执行前");
        Object invoke = method.invoke(object, args);
        log.info("执行后");
        return invoke;
    }
}
