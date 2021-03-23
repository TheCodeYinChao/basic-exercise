package cn.zyc.jvm.classload;



import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URLClassLoader;

/**
 * ！！！ 不得精髓  要学会必须要先学会应用场景 不然学的快忘记的更快
 *
 * classsloader  自定义主要目的就是为了破坏双亲委派 比如OSGi springboot中的热更新（全盘委托）
 * Created by Admin on 2019/12/24.
 */
public class ClassLoad extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        File file = new File("D:\\project\\basic-exercise\\basic-jvm\\JAVALoader\\test\\JavaB.class");

        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        InputStream is = getClass().getResourceAsStream(fileName);
        if (is == null) {
            return super.loadClass(name);
        }
        byte [] bytes = new byte[0];
        try {
            bytes = new byte[is.available()];
            IOUtils.readFully(is,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass("test.JavaB", bytes, 0, bytes.length);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        DemoClinet demoClinet = new DemoClinet();
        System.out.println(demoClinet.getClass().getClassLoader());
        System.out.println(demoClinet);

        ClassLoad classLoad = new ClassLoad();
        Class<?> aClass = classLoad.loadClass("cn.zyc.jvm.classload.DemoClinet");

        Object o = aClass.newInstance();
        System.out.println(o);

        classLoad = null;//类的卸载
        classLoad = new ClassLoad();
        System.gc(); //class本身就是个类对象，也会被垃圾回收，当引用断开后
        Class<?> aClass1 = classLoad.loadClass("cn.zyc.jvm.classload.DemoClinet");



        System.out.println(aClass==aClass1);//这是两个class对象

        Object o1 = aClass1.newInstance();
        System.out.println(o1);
        System.out.println("o1==o??"+o1==o);



    }

    /**
     * 子加载器可以使用父类加载器的东西（类） 父加载器不可以使用子类加载的东西（类） 继承
     * @throws Exception
     */
    @Test
    public  void test()throws Exception {


        ClassLoader app = ClassLoader.getSystemClassLoader();
        Class<B> aClass = (Class<B>) app.loadClass("cn.zyc.jvm.classload.B");
        B b2 = aClass.newInstance();//当使用的类是一个classloader时 是可以转换的
        b2.test();



        ClassLoad cl = new ClassLoad();
        Class b =  cl.loadClass("cn.zyc.jvm.classload.B");
        Object b1 = b.newInstance();


        Class<?> a = cl.loadClass("cn.zyc.jvm.classload.A");


        A a1 = (A) a.newInstance();

//        a1.b(b1);

    }


    /**
     * 1 什么样的类可以被加载？
     *   通过javac 编译成标准的.class文件 ，不然会报魔术错误（校验时期）
     * 2 如何加载一个非classpath的类？
     *   定义好文件根路径 然后以包路径为class的类加载的名称
     * 3 不同类加载器加载的类之间能使用吗？（不能） 命名空间不一致
     *
     *  两个类相互能用的前提是在一个classpath下
     * 4 全盘委托会因为一个类引用了另一个类 类可以使用哪个类，用的同一个类加载器
     *
     *
     * 5 当你决定创建你自己的ClassLoader时，需要继承java.lang.ClassLoader或者它的子类。
     * 在实例化每个ClassLoader对象时，需要指定一个父对象；如果没有指定的话，
     * 系统自动指定ClassLoader.getSystemClassLoader()为父对象。
     *
     *
     *
     *
     *
     */

}
