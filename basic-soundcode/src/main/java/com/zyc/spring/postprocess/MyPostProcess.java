package com.zyc.spring.postprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class MyPostProcess implements BeanPostProcessor,PriorityOrdered {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if(o instanceof Demo){
            System.out.println("spring  postProcessBeforeInitialization");
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if(o instanceof Demo){
            System.out.println("spring  postProcessAfterInitialization");
        }
        return o;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
