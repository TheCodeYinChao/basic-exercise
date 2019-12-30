package com.zyc.spring;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Admin on 2019/12/29.
 */
@Component
public class A {

    @Resource //按类型寻找，再按名称寻找  不属于自动装配的范畴
    private TestAutoType testAutoType;

    public void setTestAutoType(TestAutoType testAutoType) {
        this.testAutoType = testAutoType;
    }
}
