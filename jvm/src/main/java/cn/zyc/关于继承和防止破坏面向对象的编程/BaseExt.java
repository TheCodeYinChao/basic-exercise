package cn.zyc.关于继承和防止破坏面向对象的编程;

/**
 * dsc: BaseExt
 * date: 2021/1/15 14:16
 * author: zyc
 */
public class BaseExt extends  Base {
    private Integer a;

    public BaseExt() {
    }

    public BaseExt(Integer a) {
        super("a");
        this.a = a;
    }
}
