package com.zyc.spring;

import com.zyc.spring.importmy.MyMapperScan;
import com.zyc.spring.importmy.MyMapperScan1;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.zyc.*.**")
//@Import(ImportBeanDefinitionRegistrar.class)  //1 普通类  2 ImportSelector  3ImportBeanDefinitionRegistrar
//@MyMapperScan("")
//@MyMapperScan1()
public class AppConfig {
}
