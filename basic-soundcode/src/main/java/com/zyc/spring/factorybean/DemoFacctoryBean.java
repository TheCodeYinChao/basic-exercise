package com.zyc.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 2020/1/5.
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
