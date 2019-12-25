package com.zyc.mybaties.dynamicproxy;

import java.lang.reflect.Proxy;

public class ProxyDynamic {
    public static void main(String[] args) {
        Ihello ihello = new IhelloImpl();

        InvocationHandler handler = new InvocationHandler(ihello);
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Ihello o = (Ihello)Proxy.newProxyInstance(IhelloImpl.class.getClassLoader(), IhelloImpl.class.getInterfaces(), handler);
        o.sayHello();

    }
}
