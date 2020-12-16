package com.zyc.spring.cachethree;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * dsc: CreateBeanExample
 * date: 2020/12/7 11:17
 * author: zyc
 */
public class CreateBeanExample {

    @Test
    public void cacheThree(){
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(AppConfig.class);
        Object a = c.getBean("a");
        System.out.println(a);
    }
}
