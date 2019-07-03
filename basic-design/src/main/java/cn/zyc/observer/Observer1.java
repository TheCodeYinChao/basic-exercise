package cn.zyc.observer;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:10
 * Version ：1.0
 * Description:
 */
public class Observer1 implements Observer {
    private Subject subject;
    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    public void update(String msg) {
        System.out.println("[Get Msg :]"+msg);
    }
}
