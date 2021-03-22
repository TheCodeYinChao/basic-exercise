package cn.zyc.classload;

import java.sql.Connection;

/**
 * @author zyc
 * @date 2021/3/22
 * @time 23:54
 * @description :https://blog.csdn.net/yangcheng33/article/details/52631940
 */
public class JDBC {

    public static void main(String[] args) throws Exception{
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类
//     Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/user";
    // 通过java库获取数据库连接
        Connection conn = java.sql.DriverManager.getConnection(url, "root", "123456");
    }
}
