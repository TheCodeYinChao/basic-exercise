package cn.zyc.i加加;

/**
 * description: I加加 <br>
 * date: 2020/7/1 10:55 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 * java方法之间的参数传递是 值传递 而不是 引用传递
 */
public class I加加 {

    public static void main(String[] args) {


        i1();

    }

    public static int i() {
        int i = 0;
        i = i++;
        return i;
    }

    public static void i1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
