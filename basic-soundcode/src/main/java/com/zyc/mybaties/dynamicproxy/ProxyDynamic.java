package com.zyc.mybaties.dynamicproxy;


import java.lang.reflect.Proxy;

public class ProxyDynamic {
    public static void main(String[] args) {

        Ihello ihello = new IhelloImpl();

        InvocationHandler handler = new InvocationHandler(ihello);
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Class<?>[] interfaces = Ihello.class.getInterfaces();

        Class[] classes = {Ihello.class};
        Class<?>[] classes1 = Ihello.class.getClasses();

        Ihello o = (Ihello) Proxy.newProxyInstance(Ihello.class.getClassLoader(), classes, handler);
        o.sayHello();

    }
}
