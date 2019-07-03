package cn.zyc.chain;

public abstract class Handler {

    Handler handler;
    public Handler nextHandler(Handler handler){
        this.handler = handler;
        return handler;
    }

    abstract void executeHandler();
}
