package cn.zyc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存相关设置
 * -Xms20m
 * -Xmx20m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=D:\Heap
 */
public class HeapOOM {

    static class OOMObject {

    }


    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {

            list.add(new OOMObject());
        }
    }
}
