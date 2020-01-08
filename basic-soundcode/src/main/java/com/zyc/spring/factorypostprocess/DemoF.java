package com.zyc.spring.factorypostprocess;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("demoF")
public class DemoF {

    public DemoF() {
        System.out.println("postprocess  --  demo");
    }

    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    public void query(){
        System.out.println("query..");
    }
}
