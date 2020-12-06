package com.zyc.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *  FactoryBean 获取的对象是 注入的包含两个对象
 *   1 factorybean 本身
 *   2 getObject 所提供方法的返回实例
 *
 *   如何获取？
 *    1 & + ClassName  FactoryBean本身
 *    2 getobject 在本类示例中 就是纳入 spring容器类的DaoDemo
 *
 *   有什么意义？
 */
@Component("demoBean")
public class DemoFacctoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new DaoDemo();
    }

    @Override
    public Class<?> getObjectType() {
        return DaoDemo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
