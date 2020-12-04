package com.zyc.spring.proxyexample;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * dsc: HelloServiceImpl
 * date: 2020/12/4 17:29
 * author: zyc
 */
@Component
public class HelloServiceImpl implements HelloService {
    public void hello(){
        System.out.println("被代理方法");
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean(HelloService helloService) {
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();

        //代理的目标对象  效果同setTargetSource(@Nullable TargetSource targetSource)
        // 此处需要注意的是，这里如果直接new，那么该类就不能使用@Autowired之类的注入  因此建议此处还是从容器中去拿
        // 因此可以写在入参上（这也是标准的写法~~）
        //factoryBean.setTarget(new HelloServiceImpl());
        factoryBean.setTarget(helloService);

        // setInterfaces和setProxyInterfaces的效果是相同的。设置需要被代理的接口，
        // 若没有实现接口，那就会采用cglib去代理
        // 需要说明的一点是：这里不设置也能正常被代理（若你没指定，Spring内部会去帮你找到所有的接口，然后全部代理上~~~~~~~~~~~~）  设置的好处是只代理指定的接口
        factoryBean.setInterfaces(HelloService.class);
        //factoryBean.setProxyInterfaces(new Class[]{HelloService.class});

        // 需要植入进目标对象的bean列表 此处需要注意：这些bean必须实现类 org.aopalliance.intercept.MethodInterceptor或 org.springframework.aop.Advisor的bean ,配置中的顺序对应调用的顺序
        factoryBean.setInterceptorNames("methodAdvice");

        // 若设置为true，强制使用cglib，默认是false的
        //factoryBean.setProxyTargetClass(true);

        return factoryBean;
    }
}
