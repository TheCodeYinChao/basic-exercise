package cn.zyc.jvm.classload;

import java.net.URL;

/**
 * class load
 * dsc: ClassLoaderTest
 * date: 2020/11/30 18:59
 * author: zyc
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }


        System.out.println(ClassLoader.getSystemClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@1540e19d


        System.out.println(ClassLoader.getSystemClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

    }

}
