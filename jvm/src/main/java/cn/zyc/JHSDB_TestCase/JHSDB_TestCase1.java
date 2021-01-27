package cn.zyc.JHSDB_TestCase;

import java.io.IOException;

/**
 *-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops//关闭压缩指针

 *
 * dsc: JHSDB_TestCase
 * date: 2021/1/26 14:20
 * author: zyc  tanjiu
 */
public class JHSDB_TestCase1 {

    static class Test {
         final  ObjectHolder finalstaticObj = new ObjectHolder();

        void foo() throws IOException {
            System.out.println("done"); // 这里设一个断点
        }
    }
    private static class ObjectHolder {}
    public static void main(String[] args) throws Exception{
        //如果不加static 多次new 会有多个实例的被final对象
        Test test = new JHSDB_TestCase1.Test();
        Test test1 = new JHSDB_TestCase1.Test();
        test.foo();
    }
}
