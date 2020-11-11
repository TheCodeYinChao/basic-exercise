package com.zyc.mybaties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Admin on 2019/12/22.
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class DemoPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("自定义的插件");

        Object target = invocation.getTarget();

        Method method = invocation.getMethod();

        Object[] args = invocation.getArgs();


        Object proceed = invocation.proceed();


        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {

            return Plugin.wrap(target, this);
        } else
            return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public static void main(String[] args) {
        int[] by = new int['a'];
        System.out.println(by.length);
        /* 数组优化后会变成这样*/
        byte[] buffer = new byte['￣'];

        System.out.println(buffer.length);
        System.out.println("a".getBytes().length);

        byte[] bytes = "￣".getBytes();
        System.out.println(bytes.length);
       /* for(int i =0;i<100;i++){
            by[i]=i;
        }*/
    }
}
