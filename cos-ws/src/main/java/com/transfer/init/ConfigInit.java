package com.transfer.init;

import com.transfer.model.OscEntity;
import com.transfer.model.WsEntity;
import com.transfer.osc.MsgRetry;
import com.transfer.osc.OscServer;
import com.transfer.ws.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ConfigInit   implements CommandLineRunner {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private ConcurrentHashMap<String,OscEntity> oscList = new ConcurrentHashMap<>(); //osc client
    private ConcurrentHashMap<String,WsEntity> wsList = new ConcurrentHashMap();//ws client
    private MsgRetry msgRetry = new MsgRetry();
    private final  static  long TIMEOUT_REMVOE = 1000L * 60L * 10L; //超时移除时间


    @Override
    public void run(String... args){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("Init OSC ！！！");
                    OscServer oscServer = new OscServer(oscList, wsList, msgRetry);
                    oscServer.run(8764);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("Init OSC Error！！");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("Init WebSoceket ！！！");
                    new WebSocketServer(wsList,oscList,msgRetry).run(8765);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("Init WebSoceket Error！！");
                }
            }
        }).start();

        timeOut();//超时移除
        //retryMsg();//补偿发送
    }






    /**
     * 超时下线
     */
    public void timeOut(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run()  {
                long now = System.currentTimeMillis();

                ConcurrentHashMap<String, OscEntity> oscList = ConfigInit.this.oscList;
                if(!CollectionUtils.isEmpty(oscList)){
                    Iterator<Map.Entry<String, OscEntity>> iterator = oscList.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, OscEntity> next = iterator.next();
                        OscEntity oscEntity = next.getValue();

                        if(oscEntity.getLastTime() + TIMEOUT_REMVOE <= now){
                            iterator.remove();
                        }
                    }
                }

                ConcurrentHashMap<String, WsEntity> wsList = ConfigInit.this.wsList;
                if(!CollectionUtils.isEmpty(wsList)){
                    Iterator<Map.Entry<String, WsEntity>> iterator = wsList.entrySet().iterator();
                    while (iterator.hasNext()){

                        Map.Entry<String, WsEntity> next = iterator.next();
                        WsEntity wsEntity = next.getValue();

                        if(wsEntity.getLastTime()+TIMEOUT_REMVOE <= now){
                            wsEntity.getChannel().close();
                            iterator.remove();
                        }
                    }
                }
            }
        },0,10,TimeUnit.SECONDS);
    }


    /**
     * 消息重试
     */
    public void retryMsg(){
        msgRetry.retryMsg2Osc();
    }
}
