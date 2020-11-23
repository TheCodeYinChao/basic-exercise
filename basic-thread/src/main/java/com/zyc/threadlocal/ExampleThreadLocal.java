package com.zyc.threadlocal;

/**
 * dsc: ExampleThreadLocal
 * date: 2020/11/23 15:07
 * author: zyc
 */
public class ExampleThreadLocal {
    private static final  ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal();

    public static void main(String[] args) {
        try {
            THREAD_LOCAL.set(3);
            Integer rs = THREAD_LOCAL.get();
            System.out.println(rs);
        } finally {
            THREAD_LOCAL.remove();//清除key   弱引用的key 置为无效
        }
    }

}
