package com.zyc.spring.factorybean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Admin on 2020/1/5.
 */

public class TestDemo {

    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("com.zyc.*.**");
        //DemoFacctoryBean facctoryBean = (DemoFacctoryBean) configApplicationContext.getBean("demoBean");
        //java.lang.ClassCastException

        DaoDemo facctoryBean = (DaoDemo) configApplicationContext.getBean("demoBean");
        facctoryBean.test();


        BeanFactory beanFactory;
    }

}
