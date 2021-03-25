package cn.zyc.jvm.classload;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * dsc: CustomLoader
 * date: 2021/3/24 10:47
 * author: zyc
 */
public class CustomLoader extends URLClassLoader {
    public CustomLoader(URL[] urls) {
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
        URL url = new URL("file:/D:/project/basic-exercise/basic-jvm/JAVALoader/test/cn-1.0-SNAPSHOT.jar");

        CustomLoader c = new CustomLoader(new URL[]{url});
        Class<?> aClass1 = c.loadClass("test.JavaB");
//        Class<?> aClass = c.findClass("test.JavaB");
//        Object o = aClass.newInstance();

    }
}
