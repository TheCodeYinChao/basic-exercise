package com.zyc.spring.importmy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Admin on 2020/3/5.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ImportBeanDefinitionRegistrarMy.class)
public @interface MyMapperScan1 {
}
