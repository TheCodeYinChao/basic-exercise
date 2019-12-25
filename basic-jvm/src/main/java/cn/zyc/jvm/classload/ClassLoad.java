package cn.zyc.jvm.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Admin on 2019/12/24.
 */
public class ClassLoad extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

        InputStream is = getClass().getResourceAsStream(fileName);
        if(is == null){
            return super.loadClass(name);
        }
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[is.available()];
            is.read();
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(name,bytes,0,bytes.length);
    }

    public static void main(String[] args) throws Exception {

        ClassLoad classLoad = new ClassLoad();
        Class<?> aClass = classLoad.loadClass("cn.zyc.jvm.classload.DemoClinet");
        Object o = aClass.newInstance();
        System.out.println(o);

    }
}
