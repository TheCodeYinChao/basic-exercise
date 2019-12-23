package com.zyc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring
 */
public class TestSpring {
    public void springDemo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
    }
}
