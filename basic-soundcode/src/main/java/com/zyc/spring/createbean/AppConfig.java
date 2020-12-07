package com.zyc.spring.createbean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zyc.spring.createbean.a")
//@Import(ImportBeanDefinitionRegistrar.class)  //1 普通类  2 ImportSelector  3ImportBeanDefinitionRegistrar
//@MyMapperScan("")
//@MyMapperScan1()
public class AppConfig {
}
