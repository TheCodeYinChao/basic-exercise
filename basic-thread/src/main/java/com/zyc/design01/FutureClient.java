package com.zyc.design01;

/**
 * Created by Admin on 2019/5/12.
 */
public class FutureClient {
    public FutureData request(String param){
        FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(param);
                futureData.setRealData(realData);//设置真实执行类
                futureData.request();//执行请求
            }
        }).start();
        return futureData;
    }
}
