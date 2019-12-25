package com.zyc.mybaties.dynamicproxy;


import java.util.function.BiFunction;

public class TestJ8 {

    public int test(int a, int b, BiFunction<Integer, Integer,Integer> big){

       return  big.apply(a,b);

    }

    public static void main(String[] args) {

        TestJ8 testJ8 = new TestJ8();

        int test = testJ8.test(10, 2, (x, y) -> {
            return x + y;
        });

        System.out.println(test);
    }
}
