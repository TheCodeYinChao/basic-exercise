package com.zyc.valid;

/**
 * description: OutBinary <br>
 * date: 2020/9/2 11:28 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class OutBinary {
    public static void main(String[] args) {
        System.out.println(toBinaryString(4));
    }

    public static String toBinaryString(int num) {
        if (num == 0) return "" + 0;
        String result = "";
        // 左面0的个数
        int n = Integer.numberOfLeadingZeros(num);
        System.out.println("num <<= n" + (num <<= n));
        for (int i = 0; i < 32 - n; ++i) {
            int x = (Integer.numberOfLeadingZeros(num) == 0) ? 1 : 0;
            result += x;
            num <<= 1;
        }
        return result;
    }
}
