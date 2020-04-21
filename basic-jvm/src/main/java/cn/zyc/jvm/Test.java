package cn.zyc.jvm;

/**
 * description: Test <br>
 * date: 2020/4/21 13:02 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *     int  0  编程 integer 是null  去掉静态报错啦
 *
 */
public class Test {
    static Integer i;

    Integer aa;

    public static void main(String[] args) {
        System.out.println(i);
        int i = Test.i + 1;
    }

    public void t(){
        System.out.println(aa);

        aa+=1;
    }//静态不能访问费静态  非静态可以访问静态
}
