package cn.zyc.observer.bak;

/**
 * @author zyc
 * @date 2019/2/26 15:00
 * @Description:
 */
public class Observer1 implements Observer {
    @Override
    public void recevice(String msg) {
        System.out.println("观察者1 结收的消息：" + msg);
    }
}
