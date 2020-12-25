package cn.zyc.jvm.optimize;

import java.util.HashMap;
import java.util.Map;

/**
 * dsc: OOM
 * date: 2020/12/25 15:31
 * author: zyc
 */
public class OOM {
    public static void main(String[] args) {
        Map map= new HashMap();
        OOM M = new OOM();
        Integer i = 0;
        while (true){
            map.put(i,new Object());
            i++;
        }

    }

}
