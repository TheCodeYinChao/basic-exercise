package com.transfer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Body implements Serializable {
    private Long msgSerial;//消息序列号
    private Object dataCommand; //数据指令
}
