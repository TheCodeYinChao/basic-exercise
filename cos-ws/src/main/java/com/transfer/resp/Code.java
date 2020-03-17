package com.transfer.resp;

public enum Code {
    SUCCESS("0","成功"),ERROR_FORMATTER("1111","数据格式异常"),ERROR_SENDMSG("1112","发送数据失败"),ERROR_TARGET("1113","目标地址尚未建立连接"),ERROR_MSGISNULL("1114","消息内容为空");


    private String code;
    private String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
