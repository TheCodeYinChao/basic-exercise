package cn.zyc.垃圾收集;

import org.junit.Test;

/**
 * dsc: GCDemo
 * date: 2021/1/21 16:12
 * author: zyc 探究对象的回收
 */
public class GCDemo {
    @Test
    public void  test(){
        Object p = new Object();

        int i  =  1;
    }
    @Test
    public void  test1(){
       t();

        int i  =  1;
        while (true){

        }
    }

    public void t(){
        {
            Object a = new Object();//这个对象在这个代码块执行完成之后
            //如果触发gc的话 会回收
        }
        Object p = new Object();
    }

    public void t1(){
        Object a;
        {
            a = new Object();//这个对象不会回收

            Object b = new Object();
        }
        Object p = new Object();
    }
}
