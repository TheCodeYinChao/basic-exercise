package 性能测试;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * dsc: ArrayTest
 * date: 2021/3/1 19:02
 * author: zyc
 */
public class ArrayListTest {
    private static void test0(int n) {
        long l0 = System.currentTimeMillis();
        List<Integer> list = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        System.out.print(System.currentTimeMillis() - l0+ "   ");
    }
    private static void test01(int n) {
        long l0 = System.currentTimeMillis();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        System.out.print(System.currentTimeMillis() - l0+ "   ");
    }

    /**
     * 控制变量法测试arrylist指定容量后性能
     */
    @Test
    public  void test() {
        int i = 10;
        while (i-- > 0) {
            test01(10000000);
        }
        System.out.println();
        System.out.println("-------指定容量---------");
        int i1 = 10;
        while (i1-- > 0) {
            test0(10000000);
        }
    }


}
