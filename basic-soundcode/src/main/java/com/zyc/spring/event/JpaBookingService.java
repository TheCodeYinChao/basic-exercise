package com.zyc.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * description: JpaBookingService <br>
 * date: 2020/8/14 18:49 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
@Service("bookingService")
@Repository
public class JpaBookingService implements ApplicationContextAware {

    private ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    //省略的代码

    public void persistBooking(Booking booking) throws Exception {
        HelloWorldEvent bookingCreatedEvent = new HelloWorldEvent(this, booking);
        //触发event
        this.context.publishEvent(bookingCreatedEvent);
    }
}