package com.zyc.design01.bak;

import java.util.concurrent.TimeUnit;

/**
 * @author zyc
 * @date 2020/11/16
 * @time 22:28
 * @description :
 */
public class RealData implements Data {
    private String result;
    @Override
    public void request(String arg) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = arg + "我是结果";
    }

    @Override
    public Object getResult() {
        return result;
    }
}
