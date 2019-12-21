package com.zyc.design01;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class RealData implements Data {
    private String result;
    private String param;

    public RealData(String param) {
        this.param = param;
    }

    public void request() {
        log.info("Receive Param [{}]", this.param);
        try {
            Thread.sleep(5000);
            result = "I'm is execute result ...";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getResult() {
        return result;
    }
}
