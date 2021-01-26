package cn.zyc.JHSDB_TestCase;

import java.io.IOException;

/**
 *-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops//关闭压缩指针
 * staticObj、instanceObj、localObj这三个变量本身（而不是它们所指向的对象）存放在哪里？
 *
 * dsc: JHSDB_TestCase
 * date: 2021/1/26 14:20
 * author: zyc  tanjiu
 */
public class JHSDB_TestCase {

    static class Test {
        //staticObj随着Test的类型信息存放在方法区
        static ObjectHolder staticObj = new ObjectHolder();
        //finalstaticObj随着Test的类型信息存放在方法区
        static final  ObjectHolder finalstaticObj = new ObjectHolder();

        //instanceObj随着Test的对象实例存放在Java堆
        ObjectHolder instanceObj = new ObjectHolder();
        //finalinstanceObj随着Test的对象实例存放在Java堆
        final ObjectHolder finalinstanceObj = new ObjectHolder();
        void foo() throws IOException {
            //localObject则是存放在foo()方法栈帧的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }
    private static class ObjectHolder {}
    public static void main(String[] args) throws Exception{
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}
