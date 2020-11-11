package com.zyc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * description: UnsafeDemo <br>
 * date: 2020/4/10 11:46 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class UnsafeDemo {
    public static void main(String[] args) throws Exception {
//        operaArryObj();

        operaArryint();
    }


    /**
     * 安全的操作数组 引用类型
     */
    public static void operaArryObj() throws Exception {
        Integer[] str = new Integer[10];
        str[1] = 1;
        Class<?> ak = Integer[].class;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        long ABASE = unsafe.arrayBaseOffset(ak);//第一个元素的偏移地址
        int scale = unsafe.arrayIndexScale(ak); //数组中元素的大小占用多少字节

        if ((scale & (scale - 1)) != 0)
            throw new Error("data type scale not a power of two");//这里显示的要求是2的幂次方  为什么呢？

       /*   该方法的作用是返回无符号整型i的最高非零位前面的0的个数，包括符号位在内；
            如果i为负数，这个方法将会返回0，符号位为1.
                    比如说，10的二进制表示为 0000 0000 0000 0000 0000 0000 0000 1010
            java的整型长度为32位。那么这个方法返回的就是28
        */
        long ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);//算术位移 这里的计算指的是什么意思？
        //16+0  16+4 16+8
        long n = 1; //数组的第几个元素
        unsafe.compareAndSwapObject(str, ((long) n << ASHIFT/*代表 b乘以2^ASHIFT*/) + ABASE, 1, 2);
        for (Integer s : str) {
            System.out.println(s);
        }
    }

    /**
     * 安全的操作数组 基本类型
     */
    public static void operaArryint() throws Exception {
        int[] str = new int[10];
        str[1] = 1;
        Class<?> ak = int[].class;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        long ABASE = unsafe.arrayBaseOffset(ak);//第一个元素的偏移地址
        int scale = unsafe.arrayIndexScale(ak); //数组中元素的大小占用多少字节

        if ((scale & (scale - 1)) != 0)
            throw new Error("data type scale not a power of two");//这里显示的要求是2的幂次方  为什么呢？


       /*   该方法的作用是返回无符号整型i的最高非零位前面的0的个数，包括符号位在内；
            如果i为负数，这个方法将会返回0，符号位为1.
                    比如说，10的二进制表示为 0000 0000 0000 0000 0000 0000 0000 1010
            java的整型长度为32位。那么这个方法返回的就是28
        */
        long ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);//算术位移 这里的计算指的是什么意思？
        //16+0  16+4 16+8
        long n = 1; //数组的第几个元素
        unsafe.compareAndSwapInt(str, ((long) n << ASHIFT/*代表 b乘以2^ASHIFT*/) + ABASE, 1, 2);
        for (Integer s : str) {
            System.out.println(s);
        }
    }
}
