package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程.深入;

/**
 * dsc: Ext
 * date: 2021/1/15 16:50
 * author: zyc
 */
public abstract class AbstractExt implements HtppExute {
    private String param;

    @Override
    public String execute() {
        return execute内部(param);
    }


    abstract String execute内部(String param);

    //处理一个参数进去
    public void setParam(String param) {
        this.param = param;
    }
}
