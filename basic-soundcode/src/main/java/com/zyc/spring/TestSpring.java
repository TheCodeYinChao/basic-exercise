package com.zyc.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring
 * 三种初始化上下文环境的方法
 * 1、 ClassPathXmlApplicationContext
 * 2、 AnnotationConfigApplicationContext + JavaConfig
 * 3、 AnnotationConfigApplicationContext + 自己注册
 *
 * 区分 FactoryBean  的用法和用途：简化三方框架接入spring的复杂度，有三方自定义配置文件，来屏蔽
 * 接入spring 的复杂性。
 *
 */
public class TestSpring {
    @Test
    public void springDemo(){
// 1种
// ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
//
//        2种
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.*.**");
//        A bean = configApplicationContext.getBean(A.class);
//        TestAutoType beanNamesForType = configApplicationContext.getBean(TestAutoType.class);
//        beanNamesForType.test();
    }
}
