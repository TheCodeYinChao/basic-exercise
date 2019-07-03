package cn.zyc.chain;

public class HandlerOne extends Handler {
    @Override
    void executeHandler() {
        System.out.println("执行1");
        this.handler.executeHandler();
    }
}
