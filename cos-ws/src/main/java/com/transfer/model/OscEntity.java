package com.transfer.model;

import lombok.Data;

import java.io.Serializable;
import java.net.InetAddress;

@Data
public class OscEntity implements Serializable {
    private int port;/**端口*/
    private InetAddress address;/**ip*/
    private long lastTime;/**最后一次活动时间 ms*/
}
