package com.zyc.spring.cachethree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * dsc: B
 * date: 2020/12/14 17:46
 * author: zyc
 */
@Service
public class B {
    @Autowired
    private A a;

    public void test(){
        System.out.println("b --- call A.testa before");
        a.testa();
        System.out.println("b --- call A.testa after");

    }


    public A getA(){
        return this.a;
    }

    public void testISProxy(){

        System.out.println("测试自定义的 代理组件 是否把b也给代理啦");

    }
}
