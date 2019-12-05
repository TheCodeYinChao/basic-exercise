package com.zyc.proxy;

/**
 * Created by Admin on 2019/5/12.
 */
public class Test {

    public static void main(String[] args) {
        Invocation invocation = new Invocation();
        IUser user = new UserImpl();
        IUser o = (IUser) invocation.setAgent(user);
        o.test();
    }
}
