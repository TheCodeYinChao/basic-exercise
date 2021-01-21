package cn.zyc.垃圾收集;

import org.junit.Test;

/**
 *
 * 关于gc的回收
 *      首先判断代码作用域
 *         同作用域中，无法回收 因为无法判断 后续操作是否会引用当前实例
 *
 *         非同作用域，当局部变量为复用之前是不会回收的，当复用之后会将之前的对象给回收掉
 *
 *
 *
 *
 *
 * dsc: GCDemo
 * date: 2021/1/21 16:12
 * author: zyc 探究对象的回收
 */
public class GCDemo1 {
    @Test
    public void  test(){
        Object p = new Object();
        int i  =  1;
    }
    @Test
    public void  test1(){
        Object i  =  new Object();


        Integer a = 1;
//        int i11=2;
//        int i1=2;
    }

    public void t(){
        {
            Object a = new Object();//这个对象在这个代码块执行完成之后
            //如果触发gc的话 会回收
        }
//        Object p = new Object();
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
