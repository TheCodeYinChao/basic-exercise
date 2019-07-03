package cn.zyc.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2018/6/23.
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
     /*   Class<InvokerClass> aClass = InvokerClass.class;

        InvokerClass aClass1 = aClass.newInstance();
        Map<String,String >params = new HashMap();

        params.put("a","ds");
        params.put("b","ds");
        aClass1.method(params);*/

        Class<?> aClass = Class.forName("cn.zyc.reflect.InvokerClass");
        Map<String,String > params = new HashMap();

        params.put("a","ds");
        params.put("b","ds");
//        aClass.getMethod("");
        Method method1 = aClass.getMethod("method",Map.class);
        method1.invoke(aClass.newInstance(),params);
       /* Method[] methods = aClass.getMethods();
        for(Method method:methods){
            String name = method.getName();
            System.out.println(name);

            if(name.equals("method")){
                method.invoke(aClass.newInstance(),params);
            }
        }*/

    }
}
