package cn.zyc.jvm.classload;

public class DemoClinet extends Parent {

    private DemoClintInneter demoClintInneter = new DemoClintInneter();

    public DemoClinet() {
        demoClintInneter.sout();
    }


    public DemoClinet(int a) {
        super(a);
    }

    public DemoClinet(int a, int b) {
        System.out.println("构造方法2");
    }

    static {
        System.out.println("静态块1");
    }

    public static void main(String[] args) {
        new DemoClinet();

        int constance = Parent.CONSTANCE;

        int staticConstance = Parent.staticConstance;

    }
}
