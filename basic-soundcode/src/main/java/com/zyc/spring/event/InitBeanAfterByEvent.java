package com.zyc.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * dsc: InitBeanAfterByEvent
 * date: 2020/12/9 14:26
 * author: zyc
 */
@Component
public class InitBeanAfterByEvent implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("spring 触发的contextrefreshedevent 事件");
    }
}
