package com.zyc.spring.factorypostprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import sun.font.TrueTypeFont;

@Component
public class MyFactoryPostProcess implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition demoF = beanFactory.getBeanDefinition("demoF");
//      demoF.setPrimary(true);
        String beanClassName = demoF.getBeanClassName();
        System.out.println("工厂后置处理器");
        System.out.println("有了他我们将可以随意干预相关对象 修改对象属性等");
        System.out.println(beanClassName);
    }
}
