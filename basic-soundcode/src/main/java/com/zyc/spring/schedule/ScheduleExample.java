package com.zyc.spring.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * dsc: ScheduleExample
 * date: 2020/12/8 17:43
 * author: zyc
 */

@Component
public class ScheduleExample {

    @Scheduled(cron = "*/10 * * * * ?")
    public void schedule() throws InterruptedException {

        System.out.println("计划任务");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("计划任务结束");
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void schedule1(){
        System.out.println("计划任务1");
    }


}
