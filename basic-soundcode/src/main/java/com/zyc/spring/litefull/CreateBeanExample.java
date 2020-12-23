package com.zyc.spring.litefull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * dsc: CreateBeanExample
 * date: 2020/12/7 11:17
 * author: zyc
 */
public class CreateBeanExample {

    @Test
    public void beanFlow(){
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(AppConfig.class);
        c.register(BeanDemo.class);
        BeanDemo bean = c.getBean(BeanDemo.class);
    }
}
