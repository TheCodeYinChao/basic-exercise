package com.zyc.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostCut {

    @PostConstruct
    public void postCut(){
        System.out.println("测试postconstruct");
    }
}
