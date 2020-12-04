package com.zyc.spring.proxyexample;

import org.junit.Test;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dsc: DemoTest
 * date: 2020/12/4 17:32
 * author: zyc
 */
@Configuration
public class DemoTest {

    @Test
    public void testProxyFactoryBean(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        // 如果通过类型获取，会找到两个Bean：一个我们自己的实现类、一个ProxyFactoryBean所生产的代理类 而此处我们显然是希望要生成的代理类的  因此我们只能通过名称来(或者加上@Primary)
        //HelloService bean = applicationContext.getBean(HelloService.class);
        HelloService bean = (HelloService) applicationContext.getBean("proxyFactoryBean");
        bean.hello();

        System.out.println(bean); //com.fsx.service.HelloServiceImpl@4e50c791
        System.out.println(bean.getClass()); //class com.sun.proxy.$Proxy22 用得JDK的动态代理
        // 顺便说一句：这样也是没错得。因为Spring AOP代理出来的每个代理对象，都默认实现了这个接口（它是个标记接口）
        // 它这个也就类似于所有的JDK代理出来的，都是Proxy的子类是一样的思想~
        SpringProxy springProxy = (SpringProxy) bean;

    }


}
