package com.zyc.mybaties;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.ArrayList;

/**
 * Created by Admin on 2020/6/26.
 */
public class MetaObjectDemo {
    public static void main(String[] args) {
        User user = new User();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        user.setUser(users);
        MetaObject metaObject = SystemMetaObject.forObject(user);

        metaObject.setValue("id",100);
        metaObject.setValue("user[0].id",10011110);

        System.out.println(metaObject.getValue("id"));

        System.out.println(metaObject.getValue("user"));

    }
}
