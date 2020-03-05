package com.transfer.model;

import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;

@Data
public class WsEntity implements Serializable {
    private Channel channel;//通道
    private String addr;//地址
    private long lastTime;//最后活跃时间
}
