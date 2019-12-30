package com.zyc.juccollcetion;

import java.util.concurrent.ConcurrentHashMap;


/**
 * sizeCtl
 *
 * 0：默认值
 * -1：代表哈希表正在进行初始化
 * 大于0：相当于 HashMap 中的 threshold，表示阈值
 * 小于-1：代表有多个线程正在进行扩容
 *
 */
public class ConCurrentHashMap {

    public static void main(String[] args) {

        ConcurrentHashMap c = new ConcurrentHashMap();

        for(int i =0 ; i<20;i++){
            c.put("a"+i,"A");
        }

        c.size();

        c.remove(1);

        c.forEach((x,y)->{ });
    }
}
