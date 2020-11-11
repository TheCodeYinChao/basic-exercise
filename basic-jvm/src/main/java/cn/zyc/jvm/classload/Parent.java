package cn.zyc.jvm.classload;

public class Parent {

    public static final int CONSTANCE = 10;

    static int staticConstance = 11;

    public Parent() {
        System.out.println("p1");
    }

    public Parent(int a) {
        System.out.println("p2");
    }
}
