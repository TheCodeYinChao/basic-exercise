package com.transfer.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息指令
 */
@Data
public class MsgCommand implements Serializable {
    private int oscListenerPort; //osc 监听端口
    private String reqIp;//请求ip
    private int type;//0 连接 1 下线 2 点对点消息 3 群发消息 4消息确认
    private String targetIp;//目的IP
    private Object dataCommand;//数据指令
    private Long msgSerial;//消息序列号
    private Long sendTimeStemp;//发送时间戳
}
