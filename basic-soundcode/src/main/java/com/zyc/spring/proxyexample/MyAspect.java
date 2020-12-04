package com.zyc.spring.proxyexample;

import org.junit.Before;

/**
 * dsc: MyAspect
 * date: 2020/12/4 18:30
 * author: zyc
 */
//@Aspect
//class MyAspect {
//
//    @Pointcut("execution(* hello(..))")
//    private void beforeAdd() {
//    }
//
//    @Before("beforeAdd()")
//    public void before1() {
//        System.out.println("-----------before-----------");
//    }
//   public static void main(String[] args) {
//        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(new HelloServiceImpl());
//		// 注意：此处得MyAspect类上面的@Aspect注解必不可少
//        proxyFactory.addAspect(MyAspect.class);
//        //proxyFactory.setProxyTargetClass(true);//是否需要使用CGLIB代理
//        HelloService proxy = proxyFactory.getProxy();
//        proxy.hello();
//
//        System.out.println(proxy.getClass()); //class com.sun.proxy.$Proxy6
//    }
//}