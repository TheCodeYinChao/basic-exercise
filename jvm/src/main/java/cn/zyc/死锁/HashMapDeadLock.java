package cn.zyc.死锁;

import org.junit.Test;

import java.util.HashMap;

/**
 * dsc: HashMapDeadLock
 * date: 2020/12/18 10:54
 * author: zyc
 */
public class HashMapDeadLock {
    @Test
    public void main1() {

        HashMap hashMap = new HashMap(2);
        hashMap.put("a","");
        hashMap.put("b","");
        hashMap.put("c","");

        hashMap.get("a");

        hashMap.remove("a");
    }
    @Test
    public void main2() {
        for (int j = 0; j < 5; ++j){
            System.out.println(j);
        }
    }
}
