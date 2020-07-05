package cn.zyc.spi;

import java.util.ServiceLoader;

/**
 * Created by Admin on 2020/7/5.
 * 加载演示
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<Service> shouts = ServiceLoader.load(Service.class);
        for (Service s : shouts) {
            System.out.println(s);
        }
    }
}
