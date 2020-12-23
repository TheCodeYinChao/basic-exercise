package com.zyc.spring.schedule;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * dsc: Deom
 * date: 2020/12/8 17:44
 * author: zyc
 */
public class Deom {
    @Test
    public void initSpirng() throws IOException {
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(AppConfig.class);
        System.in.read();
    }
}
