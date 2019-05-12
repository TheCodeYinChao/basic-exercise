package com.zyc.sprdemo;

import java.lang.annotation.*;

/**
 * Created by Admin on 2019/5/12.
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface  ManagerAnnotation {
    public Class test() ;
}
