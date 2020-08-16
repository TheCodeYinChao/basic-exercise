package com.zyc.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * description: BookingEventsListener <br>
 * date: 2020/8/14 18:45 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@Component
//@Async
public class BookingEventsListener implements ApplicationListener<HelloWorldEvent> {

    //listener实现
    public void onApplicationEvent(HelloWorldEvent event) {
        //do something
        System.out.println("com.zyc.spring.event.BookingEventsListener.onApplicationEvent");
    }


//    @EventListener(HelloWorldEvent.class)
//    public void lister(HelloWorldEvent event){
//        System.out.println(event);
//    }

}