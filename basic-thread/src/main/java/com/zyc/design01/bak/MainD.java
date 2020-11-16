package com.zyc.design01.bak;

/**
 * @author zyc
 * @date 2020/11/16
 * @time 22:35
 * @description :
 */
public class MainD {
    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();

        FetureData aa = futureClient.getRs("aa");

        Object result = aa.getResult();
        System.out.println(result);

    }
}
