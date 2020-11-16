package com.zyc.design01.bak;

/**
 * @author zyc
 * @date 2020/11/16
 * @time 22:33
 * @description :
 */
public class FutureClient {
    public FetureData getRs(String arg){
        RealData realData = new RealData();
        FetureData fetureData = new FetureData(realData);
        new Thread(()->{
           fetureData.request(arg);
        }).start();
        return fetureData;
    }
}
