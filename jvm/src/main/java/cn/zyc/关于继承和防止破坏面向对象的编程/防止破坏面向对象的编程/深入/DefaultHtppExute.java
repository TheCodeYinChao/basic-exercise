package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程.深入;

/**
 * dsc: DefaultHtppExute
 * date: 2021/1/15 16:52
 * author: zyc
 */
public class DefaultHtppExute extends AbstractExt {
    @Override
    String execute内部(String param) {

        System.out.println("执行接收到的 参数 是 ："+ param);
        return "我已经处理完啦";
    }
}
