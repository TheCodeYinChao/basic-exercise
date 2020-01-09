package com.zyc.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring
 */
public class TestDemoSpring {
    @Test
    public void springDemo(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.*.**");
        A bean = configApplicationContext.getBean(A.class);

        TestAutoType beanNamesForType = configApplicationContext.getBean(TestAutoType.class);
        beanNamesForType.test();
    }
}
