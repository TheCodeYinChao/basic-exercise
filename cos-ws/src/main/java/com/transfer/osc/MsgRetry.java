package com.transfer.osc;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import com.transfer.model.MsgCommand;
import com.transfer.model.OscEntity;
import com.transfer.resp.MsgType;
import com.transfer.resp.RespVo;
import com.transfer.utils.JacksonUtil;
import com.transfer.utils.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MsgRetry {
    public Snowflake snowflake = new Snowflake(1, 1);
    /**
     * 存储补偿消息
     */
    public ConcurrentHashMap<Long, MsgCommand> retryMsg = new ConcurrentHashMap<Long, MsgCommand>();

    public ConcurrentHashMap<String, OscEntity> oscEntitys;


    public ConcurrentHashMap<Long, List<String>> ips = new ConcurrentHashMap<Long, List<String>>();

    private final int MAX_RETRY = 3;//最大补偿次数

    private final long OUT_ACK_TIME = 1000; //超时确认时间 ms


    /**
     * 存储 点对点 待重试消息
     *
     * @param msgCommand
     */
    public void putRetryMsg(MsgCommand msgCommand) {
        retryMsg.put(snowflake.nextId(), msgCommand);
    }


    /**
     * 移除 点对点 已确认消息
     *
     * @param msgSerial
     */
    public void deleteConfirmed(Long msgSerial) {
        retryMsg.remove(msgSerial);
    }


    /**
     * @param msgSerial 消息序列
     * @param ipL       群发ip地址
     */
    public void putIps(Long msgSerial, List<String> ipL) {
        ips.put(msgSerial, ipL);
    }

    /**
     * 获取群发列表
     *
     * @param msgSerial
     * @return
     */
    public List<String> getIps(Long msgSerial) {
        return ips.get(msgSerial);
    }

    /**
     * @param msgSerial 消息序列
     * @param oscIp     群发ip地址确认
     */
    public void removeIps(Long msgSerial, String oscIp) {
        List<String> ipL = getIps(msgSerial);
        if (CollectionUtils.isEmpty(ipL)) {
            ips.remove(msgSerial);
        } else {
            ipL.remove(oscIp);
            if (ipL.size() > 0) {
                ips.put(msgSerial, ipL);
            } else {
                ips.remove(msgSerial);
            }
        }
    }


    /**
     * 响应 所有消息 包含异常
     *
     * @param msg
     * @param oscEntity
     */
    public void respOscMsg(String msg, OscEntity oscEntity) {

        try {
            OSCPortOut out = new OSCPortOut(oscEntity.getAddress(), oscEntity.getPort());
            OSCMessage message = new OSCMessage();
            message.setAddress("/zlx");
            message.addArgument(msg);
            OSCBundle pack = new OSCBundle();
            pack.addPacket(message);
            out.send(pack);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("To Send OSC Error :{}", e.getMessage());
        }
    }


    private ConcurrentHashMap<String, Integer> recordRetryCount = new ConcurrentHashMap<String, Integer>();

    /**
     * 补偿发送
     */
    public void retryMsg2Osc() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<Long, MsgCommand>> iterator = retryMsg.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Long, MsgCommand> entry = iterator.next();
            Long msgSerial = entry.getKey();
            MsgCommand msg = entry.getValue();
            Long sendTimeStemp = msg.getSendTimeStemp();

            if (MsgType.ONE_TO_MORE.getType() == msg.getType()) {//群发

                List<String> ipL = ips.get(msgSerial);
                if (CollectionUtils.isEmpty(ipL)) {
                    iterator.remove();
                    ips.remove(msg.getMsgSerial());
                    continue;
                }
                Iterator<String> iteip = ipL.iterator();
                while (iteip.hasNext()) {
                    String oscIp = iteip.next();
                    Integer count = recordRetryCount.get(oscIp);
                    count = Objects.isNull(count) ? 0 : count;
                    count += 1;
                    recordRetryCount.put(oscIp, count);
                    if (count > this.MAX_RETRY) {
                        iteip.remove();
                        iterator.remove();
                        recordRetryCount.remove(oscIp);
                    }
                    long lastTime = sendTimeStemp + OUT_ACK_TIME * count;
                    if (lastTime >= now) {
                        OscEntity oscEntity = oscEntitys.get(oscIp);
                        String ack = JacksonUtil.toJson(RespVo.success(msg));
                        respOscMsg(ack, oscEntity);
                        iteip.remove();
                        recordRetryCount.remove(oscIp);
                    }
                }
            } else {
                String targetIp = msg.getTargetIp();
                Integer count = recordRetryCount.get(targetIp);
                count = Objects.isNull(count) ? 0 : count;
                count += 1;
                recordRetryCount.put(targetIp, count);
                if (count > this.MAX_RETRY) {
                    recordRetryCount.remove(targetIp);
                    iterator.remove();
                }
                long lastTime = sendTimeStemp + OUT_ACK_TIME * count;
                if (lastTime >= now) {
                    OscEntity oscEntity = oscEntitys.get(targetIp);
                    String ack = JacksonUtil.toJson(RespVo.success(msg));
                    respOscMsg(ack, oscEntity);
                    recordRetryCount.remove(targetIp);
                    iterator.remove();
                }
            }
        }
    }
}
