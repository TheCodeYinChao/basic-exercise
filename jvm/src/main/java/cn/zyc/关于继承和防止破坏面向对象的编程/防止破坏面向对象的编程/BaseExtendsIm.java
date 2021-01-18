package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程;

/**
 * dsc: BaseExtendsIm 注意这个方法实现的抽象
 * date: 2021/1/15 16:33
 * author: zyc
 */
public class BaseExtendsIm extends BaseExtends {

    public BaseExtendsIm(String a) {
        super(a);
    }

    @Override
    Object extFly(Object arg, String a) {
        System.out.println("这是个扩展方法");
        return "result";
    }
}
