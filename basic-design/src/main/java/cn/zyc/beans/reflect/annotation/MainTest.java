package cn.zyc.beans.reflect.annotation;

import java.lang.reflect.Field;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 15:03
 * Version ：1.0
 * Description:
 */
public class MainTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("nihao");

        String sql1=query(user);
        System.out.println(sql1);
    }

    public static String query(User user){
        Class<? extends User> aClass = user.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field:fields){
            boolean assignableFrom = field.isAnnotationPresent(Demo.class);
            if(assignableFrom){
                Demo annotation = field.getAnnotation(Demo.class);
                String name = annotation.name();
                System.out.println(name);
                return name;
            }
        }
        return "";
    }
}
