package cn.zyc.classload;


/**
 * @Author wenxiaoYa
 * @Date 2020/05/13  11:22
 **/
public class Test1 {


    public static void main(String[] args) {

        /**
         * 1. 下面操作 n创建了多少个对象
         */
        String str = new String("a") + new String("b");

        /**
         * 2. 执行下面语句会打印什么
         */
//        System.out.println(S1.value);  22222   1

        /**
         * 3. 执行下面语句会打印什么
         */
//        System.out.println(S1.value1);  2

        /**
         * 4. 执行下面语句会打印什么
         */
//        S[] tests = new S[1];  不打印
    }
}

class S {
    static {
        System.out.println("2222222222222");
    }

    public static int value = 1;

    public static final int value1 = 2;
}

class S1 extends S {

    static {
        System.out.println("11111111111");
    }

}