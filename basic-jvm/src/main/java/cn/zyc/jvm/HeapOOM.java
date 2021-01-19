package cn.zyc.jvm;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    public static void main(String[] args) throws Exception{
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            if(list.size()>100){
                list = new ArrayList<>();
            }
            list.add(new OOMObject());
        }
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    public void run() {
        throw new Throwable();
    }
}
