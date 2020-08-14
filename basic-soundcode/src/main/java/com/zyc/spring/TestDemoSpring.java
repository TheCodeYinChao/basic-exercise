package com.zyc.spring;

import com.zyc.spring.event.Booking;
import com.zyc.spring.event.JpaBookingService;
import com.zyc.spring.factorypostprocess.MyFactoryPostProcess;
import com.zyc.spring.importmy.ImPortBena;
import com.zyc.spring.importmy.RegistryBean;
import com.zyc.spring.postprocess.Demo;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * spring
 * 三种初始化上下文环境的方法
 * 1、 ClassPathXmlApplicationContext
 * 2、 AnnotationConfigApplicationContext + JavaConfig
 * 3、 AnnotationConfigApplicationContext + 自己注册
 *
 * 知识点1 区分 FactoryBean  的用法和用途：简化三方框架接入spring的复杂度，有三方自定义配置文件，来屏蔽
 * 接入spring 的复杂性。
 *
 * 知识点2 spring后置处理器 扩展点
 *
 * 5个扩展点
 * 1 、 BeanPostProcessor：       AnnotationConfigApplicationContext --- refresh ---prepareBeanFactory---BeanPostProcessor ---ApplicationContextAwareProcessor
 * 2 、 BeanFactoryPostProcessor：
 * 3、ImportBeanDefinitionRegistrar
 *
 * 知识点3 spring初始化的相关流程围绕着bean定义  bd    {@link BeanDefinition}
 *
 *
 * 知识点4 核心类 ConfigurationClassPostProcessor
 *
 * 知识点5 关于bd 分类问题   AnnotatedBeanDefinition  RootBeanDefinition  GenericBeanDefinition......
 *
 * 知识点6 @Import  1 普通类  2 ImportSelector  3ImportBeanDefinitionRegistrar
 *
 * 往集合里放bd
 * @see AnnotationConfigApplicationContext#register(java.lang.Class[])
 * @see #scan()
 * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar   //牛逼的类
 *
 *
 *
 */
public class TestDemoSpring {
    @Test
    public void springDemo(){
// 1种
// ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
//
//        2种
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        configApplicationContext.addBeanFactoryPostProcessor(new MyFactoryPostProcess());
//        configApplicationContext.register(Demo.class);
//        configApplicationContext.refresh();

        Demo bean = configApplicationContext.getBean(Demo.class);
        bean.query();


//        A bean = configApplicationContext.getBean(A.class);
//        TestAutoType beanNamesForType = configApplicationContext.getBean(TestAutoType.class);
//        beanNamesForType.test();

        ImPortBena bm = configApplicationContext.getBean(ImPortBena.class);
        bm.list();
        RegistryBean rb = (RegistryBean)configApplicationContext.getBean("aa");
        rb.list();
    }


    @Test
    public void springEvent() throws Exception {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /**组播时间 添加 线程池使其可以异步*/
        SimpleApplicationEventMulticaster bean = (SimpleApplicationEventMulticaster)configApplicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME);
        bean.setTaskExecutor(Executors.newSingleThreadExecutor());

        JpaBookingService rb = (JpaBookingService)configApplicationContext.getBean("bookingService");

        rb.setApplicationContext(configApplicationContext);
        Booking b = new Booking();
        b.setId("adfaqew21321fasdqw5r1");

        rb.persistBooking(b);
    }
}
