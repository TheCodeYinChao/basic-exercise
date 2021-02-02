package com.zyc.spring.proxy.cglb;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * dsc: CGLBExample  代理值只对公开的方法， 私有方法的代理是没有意义的
 * date: 2020/12/3 11:12
 * author: zyc
 */
public class CGLBExample {
    public static void main(String[] args) throws IllegalAccessException {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLBExample.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(method.getName());
                System.out.println("before method run...");

                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        enhancer.setStrategy(new BeanFactoryAwareGeneratorStrategy(null));
        enhancer.setUseCache(true);
        Object o = enhancer.create();
        Field[] fields = o.getClass().getFields();

        for (Field field : fields) {
            System.out.println(field.getName());
            if("$$beanFactory".equals(field.getName())){
                field.set(o,new DefaultListableBeanFactory());
            }
        }

        System.out.println(o);

//        CGLBExample sample = (CGLBExample)
//        sample.test();
    }

    /**
     * 这里不能用 private
     */
    public void test() {
        System.out.println("原生 test》》");
    }

}
