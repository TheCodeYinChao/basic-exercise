package com.zyc.spring;

import com.zyc.spring.postprocess.Demo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring
 * 三种初始化上下文环境的方法
 * 1、 ClassPathXmlApplicationContext
 * 2、 AnnotationConfigApplicationContext + JavaConfig
 * 3、 AnnotationConfigApplicationContext + 自己注册
 *
 * 知识点1 区分 FactoryBean  的用法和用途：简化三方框架接入spring的复杂度，有三方自定义配置文件，来屏蔽
 * 接入spring 的复杂性。
 *
 * 知识点2 spring后置处理器
 *
 * 5个扩展点
 * 1 、 BeanPostProcessor：       AnnotationConfigApplicationContext --- refresh ---prepareBeanFactory---BeanPostProcessor ---ApplicationContextAwareProcessor
 * 2 、 BeanFactoryPostProcessor：
 */
public class TestSpring {
    @Test
    public void springDemo(){
// 1种
// ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
//
//        2种
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.*.**");
//        configApplicationContext.register(Demo.class);
//        configApplicationContext.refresh();
        Demo bean = configApplicationContext.getBean(Demo.class);
        bean.query();
//        A bean = configApplicationContext.getBean(A.class);
//        TestAutoType beanNamesForType = configApplicationContext.getBean(TestAutoType.class);
//        beanNamesForType.test();
    }
}
