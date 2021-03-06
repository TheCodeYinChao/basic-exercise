package com.zyc.mybaties;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by Admin on 2020/6/26.
 */
@MapperScan
public class MetaObjectDemo {
    public static void main(String[] args) {
        User user = new User();
        MetaObject metaObject = SystemMetaObject.forObject(user);

        metaObject.setValue("id", 100);

        System.out.println(metaObject.getValue("id"));

    }
}
