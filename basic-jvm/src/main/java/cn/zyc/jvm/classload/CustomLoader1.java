package cn.zyc.jvm.classload;

import test.A;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * dsc: CustomLoader
 * date: 2021/3/24 10:47
 * author: zyc
 */
public class CustomLoader1 extends URLClassLoader {
    public CustomLoader1(URL[] urls) {
        super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args)throws Exception {
        URL url = new URL("file:/D:/project/basic-exercise/basic-jvm/JAVALoader1/test/cn-1.0-SNAPSHOT.jar");

        CustomLoader1 c = new CustomLoader1(new URL[]{url});
        Class<?> aClass1 = c.loadClass("test.JAVAB");
        A o = (A) aClass1.newInstance();
        o.t();
//        aClass1.getMethod("t").invoke(o);


    }
}
