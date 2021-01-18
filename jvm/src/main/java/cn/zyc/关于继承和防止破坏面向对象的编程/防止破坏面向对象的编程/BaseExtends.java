package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程;

/**
 * dsc: BaseExtends 这里的fly对extFly进行包装，是得原来的fly函数可以扩展出一个extFly方法儿不用通过向上转型进行调用
 * date: 2021/1/15 16:30
 * author: zyc
 */
public abstract class BaseExtends implements BaseA {
    protected String a;

    public BaseExtends() {
    }
    public BaseExtends(String a) {
        this.a = a;
    }

    @Override
    public Object fly(Object arg) {
        //这里我要扩展这个方法
        return extFly(arg,a);
    }

    abstract Object extFly(Object arg,String a);
}
