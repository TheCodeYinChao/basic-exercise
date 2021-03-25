package cn.zyc.jvm.classload.serviceloader;

import java.sql.DriverManager;

/**
 * dsc: ServiceLoad
 * date: 2021/3/25 17:26
 * author: zyc
 */
public class ServiceLoad {
    public static void main(String[] args) {
        /**
         * 1 serviceloader 什么时候开始加载？
         * {@link java.sql.DriverManager#getConnection(String, String, String)}
         * {@link DriverManager#loadInitialDrivers()}
         *  这里是通过 DriverManager cn.zyc.jvm.classload.jdbc.JDBC
         * 2 tccl 如何破坏双亲委派并加载的？
         *  按需加载时，线程上下文获取接口的加载类的classloader 用于实现类的加载
         * 3 为什么需要tccl？
         * 比如我们自己建了一个meta-inf.service 定义了一个接口  我们写入时 drivermanager 所在rt 由 bootstrap加载
         * 我们的接口在classpaht中由app 加载 是无引用到的。
         *
         */
    }
}
