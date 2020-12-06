package com.zyc.spring.factorybean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 2020/1/5.
 */

public class TestDemo {

    @Test
    public void beanFactory() {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("");

        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.spring.factorybean");


    }

    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.spring.factorybean");
        //DemoFacctoryBean facctoryBean = (DemoFacctoryBean) configApplicationContext.getBean("demoBean");
        //java.lang.ClassCastException
        DaoDemo facctoryBean = (DaoDemo) configApplicationContext.getBean("demoBean");
        facctoryBean.test();

        DemoFacctoryBean facctoryBean1 = (DemoFacctoryBean) configApplicationContext.getBean("&demoBean");//& + 名称
        System.out.println(facctoryBean1);
    }

}
