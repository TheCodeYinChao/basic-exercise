package com.zyc.design01;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        FutureClient futureClient = new FutureClient();
        FutureData futureData = futureClient.request("测试");
        FutureData futureData1 = futureClient.request("测试");
        FutureData futureData2 = futureClient.request("测试");

        log.info("execute other task ");

        log.info("execute other task ");

        log.info("execute other task ");

        String result = futureData.getResult();
        log.info("aspc execute result [{}]", result);

    }
}
