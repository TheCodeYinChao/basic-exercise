package cn.zyc.array;


/**
 * description: ArrayDemo <br>
 * date: 2020/5/26 20:09 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class ArrayDemo {
    public static void main(String[] args) throws Exception {
//        int[] a = new int[100000000];

        A[] a1 = new A[100000000];
        A a = new A();
        a1[0] = a;

        Thread.sleep(1000000);

    }

    static class A {
        int[] a = new int[100000000];
        A[] a1 = new A[100000000];


    }
}
