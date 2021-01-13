package com.zyc.spring.bd添加构造;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.FactoryBean;

import static org.springframework.util.Assert.notNull;

/**
 * dsc: MapperFactoryBean
 * {@link ClassPathMapperScanner#processBeanDefinitions(java.util.Set) }这个方法中描述出MapperFactoryBean(Class<T> mapperInterface)这个构造方法
 *
 * 注意：在引入mybaties 时分称两部分
 * 第一部分 将sqlsessionFactory 初始化，MybatisPlusAutoConfiguration
 * 通过springboot的自动配置，实现 配置自动化，集成yml中同时 生成SqlSessionFactoryBean来实现，然后getObject 拿到 里面构建的DefaultSqlSessionFactory
 * 第二部分 通过
 * spring自动装配byType ,将自动配置的DefaultSqlSessionFactory注入到我们的MapperFactoryType中
 * org.mybatis.spring.mapper.ClassPathMapperScanner#processBeanDefinitions
 * 
 * 如何填充
 * date: 2021/1/13 16:59
 * author: zyc
 */
public class MapperFactoryBean<T> extends SqlSessionDaoSupport implements FactoryBean<T> {

    private Class<T> mapperInterface;//通过springbd添加的构造方法添加进来的

    private boolean addToConfig = true;

    public MapperFactoryBean() {//默认构造spring用的这个，
        // intentionally empty
    }

    public MapperFactoryBean(Class<T> mapperInterface) {//我们可以通过构建bd时给他设置这个class
        this.mapperInterface = mapperInterface;
    }
    @Override
    protected void checkDaoConfig() {
//        super.checkDaoConfig();//这个报错，暂时注释掉 这个类就是spring与mybatis接和的类

        notNull(this.mapperInterface, "Property 'mapperInterface' is required");

        Configuration configuration = getSqlSession().getConfiguration();
        if (this.addToConfig && !configuration.hasMapper(this.mapperInterface)) {
            try {
                configuration.addMapper(this.mapperInterface);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }
    //构建bd的时候加进来的 private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public T getObject() throws Exception {
        return getSqlSession().getMapper(this.mapperInterface);
    }
    @Override
    public Class<T> getObjectType() {
        return this.mapperInterface;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }


    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }
    public boolean isAddToConfig() {
        return addToConfig;
    }
}

