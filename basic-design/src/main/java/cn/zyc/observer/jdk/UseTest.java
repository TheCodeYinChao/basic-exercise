package cn.zyc.observer.jdk;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:24
 * Version ：1.0
 * Description:
 */
public class UseTest {
    public static void main(String[] args) {
        Subject1 s = new Subject1();
        Observer1 o = new Observer1();
        o.registerSubject(s);
        s.setMsg("测试");
        s.getMsg();
    }
}
