package com.zyc.design01.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info(" task execute ...");
        Thread.sleep(5000);
        return 010101;
    }
}
