package com.zyc.spring.proxyexample;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * dsc: MethodBeforeAdviceDemo
 * date: 2020/12/4 17:30
 * author: zyc
 */
@Component("methodAdvice")
public class MethodBeforeAdviceDemo implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        System.out.println("method before...");
    }
}
