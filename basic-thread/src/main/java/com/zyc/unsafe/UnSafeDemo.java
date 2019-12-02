package com.zyc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Admin on 2019/11/27.
 */
public class UnSafeDemo {
    public static void main(String[] args) throws Exception {

        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        System.out.println(unsafe);
        User user = new UnSafeDemo().new User();

        long i = unsafe.objectFieldOffset(User.class.getField("i"));

        System.out.println(i);
        unsafe.compareAndSwapInt(user,i,0,12);
        System.out.println(user.getI());

        Modifier
    }

    class  User{
        public int i;

        public int getI() {
            return i;
        }
    }
}
