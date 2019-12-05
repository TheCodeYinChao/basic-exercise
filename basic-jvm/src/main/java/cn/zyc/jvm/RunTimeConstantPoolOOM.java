package cn.zyc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区 运行时常量池
 * -XX: PermSize=10M -XX: MaxPermSize=10M
 */
public class RunTimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());

        }
    }
}
