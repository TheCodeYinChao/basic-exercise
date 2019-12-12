package com.cos.ws.cosws.osc;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息指令
 */
@Data
public class MsgCommand implements Serializable {
    private String reqIp;//请求ip
    private int type;//0 连接 1 下线
    private String targetIp;//目的IP
    private Object dataCommand;//数据指令
}
