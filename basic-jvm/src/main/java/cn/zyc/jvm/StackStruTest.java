package cn.zyc.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @Author wenxiaoYa
 * @Date 2020/04/16  14:17
 **/
public class StackStruTest {

    static {
        num = 10;
        //System.out.println("====="+num);
    }
    private static int num = 20;

    private static int two = 21;

    public static void main(String[] args) {
        System.out.println(num);
    }
}
