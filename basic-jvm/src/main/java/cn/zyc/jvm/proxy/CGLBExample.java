package cn.zyc.jvm.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * dsc: CGLBExample  代理值只对公开的方法， 私有方法的代理是没有意义的
 * date: 2020/12/3 11:12
 * author: zyc
 */
public class CGLBExample {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLBExample.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        CGLBExample sample = (CGLBExample) enhancer.create();
        sample.test();
    }

    /**
     * 这里不能用 private
     */
    public void test() {
        System.out.println("原生 test》》");
    }

}
