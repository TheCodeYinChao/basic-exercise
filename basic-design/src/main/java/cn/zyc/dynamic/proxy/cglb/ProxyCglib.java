package cn.zyc.dynamic.proxy.cglb;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zyc
 * @date 2019/3/22 16:51
 * @Description:
 */
public class ProxyCglib implements MethodInterceptor {
    private Object target;

    public Object getInstance(Object o) {
        this.target =o;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglb befor");
        Object invoke = methodProxy.invoke(target, objects);
        System.out.println("cglb after");

        return  invoke;
    }


}
