package cn.zyc.垃圾收集;

import java.io.IOException;
import java.lang.ref.Reference;

/**
 * dsc: GarbageCollection
 * date: 2020/12/22 19:12
 * author: zyc
 */
public class GarbageCollection {
    public static void main(String[] args) throws IOException {
        Reference reference;

        System.gc();
        System.in.read();

    }
}
