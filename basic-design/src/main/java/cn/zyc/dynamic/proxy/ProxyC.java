package cn.zyc.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 13:45
 * Version ：1.0
 * Description: 基础代理类的  InvocationHandler Proxy
 */
public class ProxyC implements InvocationHandler {
    private  Object object;

    public  Object newInstance(Object object){
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                                        object.getClass().getInterfaces(),
                this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前。。。。。");
        Object invoke = method.invoke(object, args);
        System.out.println("代理后。。。。。");
        return invoke;
    }
}
