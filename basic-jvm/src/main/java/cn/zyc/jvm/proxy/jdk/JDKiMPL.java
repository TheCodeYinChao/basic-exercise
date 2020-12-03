package cn.zyc.jvm.proxy.jdk;

/**
 * dsc: JDKiMPL
 * date: 2020/12/3 11:35
 * author: zyc
 */
public class JDKiMPL implements JdkExample {
    @Override
    public void test1() {
        System.out.println("test 原生方法");
    }
}
