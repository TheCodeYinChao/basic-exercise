package com.zyc.mybaties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * Created by Admin on 2019/12/22.
 */
@Intercepts({
//        @Signature(type = StatementHandler.class,method = "prepare" ,args = {Connection.class})
})
public class DemoPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
