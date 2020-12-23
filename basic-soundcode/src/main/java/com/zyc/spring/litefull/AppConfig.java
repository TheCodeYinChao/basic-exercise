package com.zyc.spring.litefull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zyc.spring.litefull")
//@Import(ImportBeanDefinitionRegistrar.class)  //1 普通类  2 ImportSelector  3ImportBeanDefinitionRegistrar
//@MyMapperScan("")
//@MyMapperScan1()
public class AppConfig {

    /**
     * 什么是 lite？
     * @bean ！+ @Configuration bean 上面不加 configuration
     *
     * 该模式下，配置类本身不会被CGLIB增强，放进IoC容器内的就是本尊
     * 该模式下，对于内部类是没有限制的：可以是Full模式或者Lite模式
     * 该模式下，配置类内部不能通过方法调用来处理依赖，否则每次生成的都是一个新实例而并非IoC容器内的单例
     * 该模式下，配置类就是一普通类嘛，所以@Bean方法可以使用private/final等进行修饰（static自然也是阔仪的）
     * 什么是 full？
     *
     *@bean + @Configuration bean所在类上加configuration
     *该模式下，配置类会被CGLIB增强，放进IoC容器内的，拿到的是代理类 单例的
     *
     *
     * 使用建议
     * 了解了Spring配置类的Full模式和Lite模式，那么在工作中我该如何使用呢？
     * 如果是在公司的业务功能/服务上做开发，使用Full模式
     * 如果你是个容器开发者，或者你在开发中间件、通用组件等，那么使用Lite模式是一种更被推荐的方式，它对Cloud Native更为友好
     * @return
     */
    @Bean
    public BeanDemo getBeanDemo(){
        return new BeanDemo();
    }
}
