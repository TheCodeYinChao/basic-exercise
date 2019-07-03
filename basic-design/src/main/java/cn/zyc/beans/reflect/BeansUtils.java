package cn.zyc.beans.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 14:01
 * Version ：1.0
 * Description:
 */
public class BeansUtils {
    public static <T> T handleBeans(T t)throws Exception{

        Class<?> aClass = t.getClass();
        Object o = aClass.newInstance();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field field :declaredFields){
            String fieldName = field.getName();
            System.out.println(fieldName);
            Method method = aClass.getDeclaredMethod("set" + StringUtil.getStr(fieldName), field.getType());
            method.invoke(o,"nihao");
        }
        System.out.println(o.toString());

        Method[] declaredMethods = aClass.getDeclaredMethods();
        Class<?> superclass = aClass.getSuperclass();
        return null;
    }

    public static void main(String[] args)throws Exception {
        handleBeans(new User());
    }

    static  class  StringUtil{

        public static String getStr(String filed){
            String substring = filed.substring(0, 1);
            String s = substring.toUpperCase() + filed.substring(1, filed.length());
            return s;
        }
    }
}
