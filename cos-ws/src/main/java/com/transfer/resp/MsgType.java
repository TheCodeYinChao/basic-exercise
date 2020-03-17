package com.transfer.resp;

public enum MsgType {
    CONNECT(0),CLOSE(1),ONE_TO_ONE(2),ONE_TO_MORE(3),MSG_CONFIRM(4);

    MsgType(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
