package com.zyc.proxy;

/**
 * Created by Admin on 2019/5/12.
 */
public class UserImpl implements IUser {
    @Override
    public void test() {
        System.out.println("测试动态代理！");
    }
}
