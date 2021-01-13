package com.zyc.spring.cachethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * dsc: A
 * date: 2020/12/14 17:46
 * author: zyc
 */
@Service
@DependsOn({"c"})
public class A {
    @Autowired
    private B b;

    public void testa(){
        System.out.println("hello word!!A");
    }
}
