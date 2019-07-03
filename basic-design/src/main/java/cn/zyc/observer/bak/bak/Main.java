package cn.zyc.observer.bak.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject1();

        subject.re( new Observer1());
        subject.re(new Observer1());
        subject.ny("你好");
    }
}
