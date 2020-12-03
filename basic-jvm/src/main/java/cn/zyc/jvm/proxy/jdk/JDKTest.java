package cn.zyc.jvm.proxy.jdk;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * dsc: JDKTest
 * date: 2020/12/3 11:38
 * author: zyc
 * jdk 是基于继承的
 *
 * aop
 * 切点
 *
 * 连接点
 *
 * 切面
 *
 * 织入
 *
 * 前置通知
 * 后置通知
 * 环绕通知
 *
 *
 */
public class JDKTest {
    @Test
    public void Jkd(){


        Class[] i = {JdkExample.class};
        /**
         *这里的classloader 可以自定义
         */
        JdkExample o = (JdkExample)Proxy.newProxyInstance(JdkExample.class.getClassLoader(),i, new InvocationHandler() {//执行的逻辑
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(" before proxy");
                Object rs = method.invoke(new JDKiMPL(), null);//被增强的切点
                System.out.println( "after proxy");
                return rs;
            }
        });
        o.test1();

    }
}
