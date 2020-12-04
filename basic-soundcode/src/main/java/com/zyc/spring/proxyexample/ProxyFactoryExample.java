package com.zyc.spring.proxyexample;

import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * dsc: ProxyFactoryExample
 * date: 2020/12/4 18:24
 * author: zyc
 */
public class ProxyFactoryExample {

    @Test
    public void test(){
        ProxyFactory proxyFactory = new ProxyFactory(new HelloServiceImpl());

        // 添加两个Advise，一个匿名内部类表示
        proxyFactory.addAdvice((AfterReturningAdvice) (returnValue, method, args1, target) ->
                System.out.println("AfterReturningAdvice method=" + method.getName()));
        proxyFactory.addAdvice(new MethodBeforeAdviceDemo());

        HelloService proxy = (HelloService) proxyFactory.getProxy();
        proxy.hello();
    }
}
