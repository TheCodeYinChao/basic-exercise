package com.zyc.mybaties.intercept;


import java.lang.reflect.InvocationTargetException;

public class IntercepterImpl implements Intercept {

    @Override
    public Object interce(Invocation invocation) {
        System.out.println("拦截1");
        try {

           return  invocation.process();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object register(Object target) {
        return TargetProxy1.waper(target,this);
    }
}