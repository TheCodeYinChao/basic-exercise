package cn.zyc.关于继承和防止破坏面向对象的编程.防止破坏面向对象的编程.深入;

/**
 * dsc: HtppExuteFactory
 * date: 2021/1/15 16:49
 * author: zyc
 */
public class HtppExuteFactory {

   static  HtppExute createHtppExute(String param){
        AbstractExt a = new DefaultHtppExute();
        a.setParam(param);
        return a;
    }

}
