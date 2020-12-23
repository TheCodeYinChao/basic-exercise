package cn.zyc.垃圾收集;

import org.junit.Test;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * dsc: GarbageCollection
 * date: 2020/12/22 19:12
 * author: zyc
 */
public class GarbageCollection {
    public static void main(String[] args) throws IOException {
        Reference reference;
        FinalDemo f = new FinalDemo();
        /**
         * //这里将f置为空切断了引用 gc是会回收，第一次会调用finalize() 方法.这里面我们可以做一些事情
         * 擦除 或者为这个f从重新建立引用，防止回收
         *
         * 主要是对gc可以验证我们的类作一些额外的工作。
         * finalize() 方法里用本地方法调用它 什么意思？ 就是 用c c++去调用
         *
         */
        f=null;
        System.gc();
        System.in.read();

    }

    @Test
    public void enqueue(){
        ReferenceQueue referenceQueue = new ReferenceQueue();
    }
}
