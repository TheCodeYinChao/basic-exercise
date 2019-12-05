package com.zyc.genericity;

/**
 * Created by Admin on 2019/5/12.
 */
public interface IDemo<T extends SuperDemo> {
    public T getT();
}
