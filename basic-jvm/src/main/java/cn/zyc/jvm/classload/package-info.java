/**
 * dsc: package-info
 * date: 2021/3/25 17:14
 * author: zyc
 */


/**
 * 1 什么样的类可以被加载？
 *   通过javac 编译成标准的.class文件 ，不然会报魔术错误（校验时期）
 * 2 如何加载一个非classpath的类？
 *   定义好文件根路径 然后以包路径为class的类加载的名称
 * 3 不同类加载器加载的类之间能使用吗？（不能） 命名空间不一致 全盘委托导致不是一个类加载器的class对象并不是同一个类
 *
 *  两个类相互能用的前提是在一个classpath下 并且加入到类加载器本类及父类的类加载器中（不破坏全盘委托下）
 *
 * 4 全盘委托会因为一个类引用了另一个类 类可以使用哪个类，用的同一个类加载器
 *
 * 5 当你决定创建你自己的ClassLoader时，需要继承java.lang.ClassLoader或者它的子类。
 * 在实例化每个ClassLoader对象时，需要指定一个父对象；如果没有指定的话，
 * 系统自动指定ClassLoader.getSystemClassLoader()为父对象。
 *
 * 6 如何模块化的插拔，正常讲只要你的类加载到内存中就是可以被使用的 ，但是 再idea中的校验逻辑中我们通过
 *  CustomLoader1 c = new CustomLoader1(new URL[]{url});
 *         Class<?> aClass1 = c.loadClass("test.JAVAB");
 *         A o = (A) aClass1.newInstance();
 *         o.t();
 *
 *假如 A 不在我们的类路径中，我们在打包执行等都会出现类找不到异常，那该怎么办呢？
 *  1 面向接口
 *  2 修改插件的校验逻辑 这个很难
 *
 * 7 我们的定义类加载器改如何使用呢？ 就是何时去触发类加载器的使用？
 *
 * 类启动时区加载我们所用的业务class
 *
 *
 *
 *
 *
 *
 *
 */
package cn.zyc.jvm.classload;