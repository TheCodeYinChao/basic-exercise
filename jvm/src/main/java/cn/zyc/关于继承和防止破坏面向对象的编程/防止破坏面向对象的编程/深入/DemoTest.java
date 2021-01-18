package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程.深入;

/**
 * dsc: DemoTest 这样真的牛逼，之前为毛没发现呢
 * date: 2021/1/15 16:54
 * author: zyc
 */
public class DemoTest {
    public static void main(String[] args) {
        HtppExute htppExute = HtppExuteFactory.createHtppExute("我是工厂创建时的一个参数");
        String rs = htppExute.execute();
        System.out.println("结果："+rs);
    }
}
