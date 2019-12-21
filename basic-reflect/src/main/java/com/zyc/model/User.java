package com.zyc.model;

import lombok.Data;

/**
 * Created by Admin on 2019/5/12.
 */
@Data
public class User {
    private String name;
    private int age;

    public String desc;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }
}
