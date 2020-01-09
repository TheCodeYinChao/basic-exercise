package com.zyc.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.zyc.*.**")
public class AppConfig {
}
