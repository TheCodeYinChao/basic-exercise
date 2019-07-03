package cn.zyc.chain;

public class HandlerTwo extends Handler {
    @Override
    void executeHandler() {
        System.out.println("execute 2");
        handler.executeHandler();
    }
}
