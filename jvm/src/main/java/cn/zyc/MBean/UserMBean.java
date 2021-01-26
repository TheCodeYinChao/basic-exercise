package cn.zyc.MBean;

/**
 * dsc: UserMBean
 * date: 2021/1/26 16:34
 * author: zyc
 */
public interface UserMBean {
    String getName();
    void SetName(String name);
    String getPasswd();
    void SetPasswd(String pwd);
    int add(int x, int y);
}
