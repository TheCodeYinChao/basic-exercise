package com.zyc.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * description: HelloWorldEvent <br>
 * date: 2020/8/14 18:44 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class HelloWorldEvent extends ApplicationEvent {
    private static final long serialVersionUID = 3039313222160544111L;

    private Booking booking;

    public HelloWorldEvent(Object source) {
        super(source);
    }

    public HelloWorldEvent(Object source, Booking booking) {
        super(source);
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

}
