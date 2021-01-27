package cn.zyc.JHSDB_TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops//关闭压缩指针

 *
 * dsc: JHSDB_TestCase
 * date: 2021/1/26 14:20
 * author: zyc  tanjiu
 */
public class JHSDB_TestCase1 {

    static  class Test extends aa{

        void foo() throws IOException {
            System.out.println("done"); // 这里设一个断点
        }
    }

    static abstract class aa{
        //final 修饰的引用类型 在当前实例对象的变量地址无法改变 但是会因为 多次的aa实例 ，存在被final 修饰的多个实例对象
        /**
         * 也就是说我们不加 static 在 多个对象中 即使是被final 修饰也会出现多
         * 份对象实例，但是如果我们想只有一份实例就要 加static 跟随aa 类的类对象而只有一份
         */
        final  ObjectHolder finalstaticObj = new ObjectHolder();
    }

    private static class ObjectHolder {
        private byte[] a= new byte[1024*1024*1];//1m
    }
    public static void main(String[] args) throws Exception{
        //如果不加static 多次new 会有多个实例的被final对象
        Test test = new JHSDB_TestCase1.Test();//这个实例的final对象已经被回收啦
        test = null;
        Test test1 = new JHSDB_TestCase1.Test();//这个里面的大对象直接放到了老年代中
        while (true){}
//        System.out.println(test.finalstaticObj == test1.finalstaticObj); //false
    }
}
