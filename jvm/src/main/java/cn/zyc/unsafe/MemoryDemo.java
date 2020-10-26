package cn.zyc.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;

import javax.xml.transform.Source;
import java.lang.reflect.Field;

/**
 * description: MemoryDemo <br>
 * date: 2020/8/26 20:59 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class MemoryDemo {

    @Test
    public void test1() throws Exception {
        //1.最简单的使用方式是基于反射获取Unsafe实例
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        long address = unsafe.allocateMemory(8L);
        System.out.println(address);

        //初始化内存填充1
        unsafe.setMemory(address, 8L, (byte) 1);
        byte aByte = unsafe.getByte(address);
        System.out.println("add byte to memory1:" + aByte);
        //测试输出
        System.out.println("add byte to memory:" + unsafe.getInt(address));
        //设置0-3 4个byte为0x7fffffff
        unsafe.putInt(address, 121);
        //设置4-7 4个byte为0x80000000
        unsafe.putInt(address + 4, 222);


        int anInt = unsafe.getInt(address);
        System.out.println(anInt);

        int anInt1 = unsafe.getInt(address + 4);
        System.out.println(anInt1);
        unsafe.allocateMemory(address);
        int anInt21 = unsafe.getInt(address + 4);
        System.out.println(anInt21);


    }
}
