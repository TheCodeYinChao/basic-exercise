package cn.zyc.array;

/**
 * description: ArrayDemo1 <br>
 * date: 2020/7/7 12:13 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *
 *     https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.newarray
 *
 *     newarray 后面的数组代表类型标识
 */
public class ArrayDemo1 {
    public static void main(String[] args) {
        int[] I = new int[-1];


        int[] a = new int[100000];
        long[] a2 = new long[2];
        double[] a3 = new double[2];
        byte[] a34 = new byte[2];
        boolean[] a4 = new boolean[2];
    }
}
