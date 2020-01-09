package com.zyc.spring;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.zyc.*.**")
//@Import(ImportBeanDefinitionRegistrar.class)  //1 普通类  2 ImportSelector  3ImportBeanDefinitionRegistrar
public class AppConfig {
}
