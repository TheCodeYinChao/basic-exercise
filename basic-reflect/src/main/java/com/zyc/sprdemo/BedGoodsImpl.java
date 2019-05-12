package com.zyc.sprdemo;

/**
 * Created by Admin on 2019/5/12.
 */
public class BedGoodsImpl implements IGoods {
    @Override
    public void buy() {
        System.out.println("注入了床这个bean");
    }
}
