package cn.zyc.spring;

import cn.zyc.AppConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
@MapperScan("cn.zyc.dao")
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.start();

//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("");
    }
}
