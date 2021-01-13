package com.zyc.spring.cachethree;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * dsc: BeanPostSmart
 * date: 2021/1/12 10:39
 * author: zyc
 */
public class BeanPostSmart extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if(beanName.equals("a")){
//            Class<?>[] classes = bean.getClass().getInterfaces();
//            Object o = Proxy.newProxyInstance(bean.getClass().getClassLoader(), classes, new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    Object invoke = method.invoke(bean, args);
//                    return invoke;
//                }
//            });

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("自定义 a 的代理对象 方法执行 前 ");
                    Object invoke = method.invoke(bean, objects);
                    System.out.println("自定义 a 的代理对象 方法执行 后 ");
                    return invoke;
                }
            });
            return enhancer.create();
        }else {
         return super.getEarlyBeanReference(bean, beanName);
        }

    }
}
