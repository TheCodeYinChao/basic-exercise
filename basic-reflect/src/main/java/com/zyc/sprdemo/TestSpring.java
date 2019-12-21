package com.zyc.sprdemo;

/**
 * Created by Admin on 2019/5/12.
 */
public class TestSpring {
    public static void main(String[] args) {
        ServiceImpl bean = SpringContainer.getBean();
        bean.buy_Test();
    }
}
