package com.transfer.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespVo implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static RespVo success(Object data) {
        return new RespVo(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), data);
    }

    public static RespVo success(String msg, Object data) {
        return new RespVo(Code.SUCCESS.getCode(), msg, data);
    }


    public static RespVo error(Code code, Object data) {
        return new RespVo(code.getCode(), code.getMsg(), data);
    }


    public static RespVo error(Code code, String msg, Object data) {
        return new RespVo(code.getCode(), msg, data);
    }


    public RespVo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
