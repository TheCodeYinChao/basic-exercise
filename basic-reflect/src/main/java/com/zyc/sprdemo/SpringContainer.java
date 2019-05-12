package com.zyc.sprdemo;

import java.lang.reflect.Method;

/**
 * Created by Admin on 2019/5/12.
 */
public class SpringContainer {
    public static ServiceImpl getBean() {

        ServiceImpl bean = new ServiceImpl();//spring预先帮你创建实例
        boolean isAnnotation = ServiceImpl.class.isAnnotationPresent(Service.class);

        if (isAnnotation) {

            Method[] method = ServiceImpl.class.getDeclaredMethods();

            for (Method method2 : method) {
                if (method2.isAnnotationPresent(ManagerAnnotation.class)) {
                    ManagerAnnotation managerAnnotation = method2.getAnnotation(ManagerAnnotation.class);
                    System.out.println("设值注入AnnotationTest(注入的方法field=" + method2.getName()
                            + ",注入的值test=" + managerAnnotation.test() + ")");
                    try {
                        Class<?> clazz = managerAnnotation.test();//反射获取该类class
                        IGoods iGoods = (IGoods) clazz.newInstance();//构造实例
                        bean.setUserdao(iGoods);//值注入--实例的属性注入

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return bean;
    }
}
