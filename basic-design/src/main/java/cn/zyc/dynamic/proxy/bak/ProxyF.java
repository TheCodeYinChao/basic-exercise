package cn.zyc.dynamic.proxy.bak;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zyc
 * @date 2018/6/19 18:55
 * @Description:
 */
public class ProxyF implements InvocationHandler {
    private Object object;

    public Object instance(Object object){
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                 this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法前");
        Object rs = method.invoke(object,args);
        System.out.println("方法后");
        return rs;
    }
}
