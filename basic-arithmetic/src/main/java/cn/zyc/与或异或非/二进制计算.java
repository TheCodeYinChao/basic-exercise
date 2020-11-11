package cn.zyc.与或异或非;

/**
 * description: 二进制计算 <br>
 * date: 2020/10/29 11:39 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class 二进制计算 {
    public static void main(String[] args) {
        与();
        或();
        异或();
        非();
    }

    public static void 与(){
            int a = 10;
            int b = 20;
            int i = a & b; //都为1 则1 否则为0
        System.out.println(i);

    }

    public static void 或(){
        int a = 10;
        int b = 20;
        int i = a | b;//有1则1
        System.out.println(i);
    }
    public static void 异或(){
        int a = 10;
        int b = 20;
        int i = a ^ b; // 0^0 1  0^1 0 1^1 1 1^0 0
        System.out.println(i);
    }
    public static void 非(){
        boolean b = false;
        boolean i = !b;
        System.out.println(i);
    }
}
