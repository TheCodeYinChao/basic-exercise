package cn.zyc.jvm.strjoin;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * dsc: StrExample
 * date: 2020/12/2 16:50
 * author: zyc
 */
public class StrExample implements AA{
    private static int field1=1;

    @Test
    public void str(){
        Map<String,Object> map = new HashMap<>();

        Object o = map.get("");

        String str = o + "";
    }
}
