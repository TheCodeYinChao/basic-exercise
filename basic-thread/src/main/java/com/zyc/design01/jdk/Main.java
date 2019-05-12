package com.zyc.design01.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<Integer>> futures = new ArrayList<>();

        futures.add(executor.submit(new Task()));
        futures.add(executor.submit(new Task()));

        for(Future<Integer> future:futures){
            Integer rs = future.get();
            log.info("result [{}]",rs);
        }
    }
}
