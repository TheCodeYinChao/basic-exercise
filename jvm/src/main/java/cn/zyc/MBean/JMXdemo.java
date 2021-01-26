package cn.zyc.MBean;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 *
 * -Dcom.sun.management.jmxremote
 *
 * 例如 {@link sun.management.MemoryImpl}
 *jmx 的管理工具
 * dsc: JMXdemo
 * date: 2021/1/26 16:34
 * author: zyc
 */
public class JMXdemo {

    public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
        MBeanServer server=ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName=new ObjectName("jmx:type=User");
        User bean=new User();
        server.registerMBean(bean, objectName);
        String oldName=null;
        String oldPwd=null;
        System.out.println("jmx started!!!");
        while(true){
            if(oldName!=bean.getName()|| oldPwd !=bean.getPasswd()){
                System.out.println(bean.getName()+":"+bean.getPasswd());
                oldName=bean.getName();
                oldPwd=bean.getPasswd();
            }
            Thread.sleep(1000);
        }
    }
}
