package com.zyc.impl;

import com.zyc.model.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Admin on 2019/5/12.
 */
@Slf4j
public class ImplReflect {
    public static void main(String[] args) throws  Exception{
        Class<?> aClass = Class.forName("com.zyc.model.User");
//        Class<? extends User> aClass = new User().getClass();
//        Class<User> aClass = User.class;

        execute(aClass);
    }

    public static void execute(Class c){
        getField(c);
        getConstract(c);
        getAnnotion(c);
        getMethod(c);
    }


    public static void getField(Class c){
        log.info("获取变量....");

        Field[] fields = c.getFields();
        for(Field field : fields){
            log.info(field.getName());
        }
        log.info("------------");

        Field[] dFields = c.getDeclaredFields();
        for(Field dField : dFields){
            log.info(dField.getName());
        }
        log.info("-------------------------------------");
    }

    public static void getConstract(Class c){
        log.info("获取抽象方法....");
        Constructor[] constructors = c.getConstructors();
        for(Constructor constructor : constructors){
            log.info(constructor.getName());
        }
        log.info("------------");
        Constructor[] dConstructors = c.getDeclaredConstructors();
        for(Constructor dConstructor : dConstructors){
            log.info(dConstructor.getName());
        }
        log.info("-------------------------------------");
    }

    public static void getAnnotion(Class c){
        log.info("获取有关注解....");
        Annotation[] annotations = c.getAnnotations();
        for(Annotation annotation : annotations){
            log.info(annotation.toString());
        }
        log.info("------------");
        Annotation[] dAnnotations = c.getDeclaredAnnotations();
        for(Annotation dAnnotation : dAnnotations){
            log.info(dAnnotation.toString());
        }
        log.info("-------------------------------------");
    }

    public static void getMethod(Class c){
        log.info("获取方法....");

        Method[] methods = c.getMethods();
        for(Method method : methods){
            log.info(method.getName());
        }
        log.info("------------");
        Method[] dMethods = c.getDeclaredMethods();
        for(Method dMethod : dMethods){
            log.info(dMethod.getName());
        }
        log.info("-------------------------------------");
    }

    public static void getSuper(Class c){
        Class cSuperclass = c.getSuperclass();
    }
}
