package cn.zyc.biginteger;

import java.math.BigInteger;

/**
 * dsc: BitBigInteger
 * date: 2021/1/22 17:30
 * author: zyc
 */
public class BitBigInteger {
    public static void main(String[] args) {
        BigInteger b = new BigInteger("0");

        BigInteger bigInteger = b.setBit(2<<25);

        System.out.println(bigInteger.testBit(2<<25));
    }
}