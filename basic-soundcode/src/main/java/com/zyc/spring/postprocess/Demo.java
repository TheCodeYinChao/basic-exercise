package com.zyc.spring.postprocess;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Demo {

    public Demo() {
        System.out.println("postprocess  --  demo");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    public void query() {
        System.out.println("query..");
    }
}
