package cn.zyc.observer;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:12
 * Version ：1.0
 * Description:
 */
public class UseTest {
    public static void main(String[] args) {
        Subject1 subject = new Subject1();
        Observer observer = new Observer1(subject);
        subject.setMsg("测试");
    }
}
