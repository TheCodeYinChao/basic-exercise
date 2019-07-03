package cn.zyc.observer.bak;

/**
 * @author zyc
 * @date 2019/2/26 15:05
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Subject s = new Subject1();
        s.register(new Observer1());
        s.nitify("hello ");
    }
}
