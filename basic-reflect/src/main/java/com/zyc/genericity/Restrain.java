package com.zyc.genericity;

/**
 * Created by Admin on 2019/5/12.
 */
public class Restrain<E extends IDemo> {
    private E e;

    public void handle(IDemo<? extends SuperDemo> demo) {

    }
}
