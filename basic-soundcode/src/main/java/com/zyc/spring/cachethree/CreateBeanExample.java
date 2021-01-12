package com.zyc.spring.cachethree;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * dsc: CreateBeanExample  循环依赖
 * 1、构造器注入循环依赖
     * @Service
     * public class A {
     *     public A(B b) {
     *     }
     * }
     * @Service
     * public class B {
     *     public B(A a) {
     *     }
     * }
 *
 * 项目启动失败抛出异常BeanCurrentlyInCreationException  这个无法解决
 * 根本原因：Spring解决循环依赖依靠的是Bean的“中间态”这个概念，而这个中间态指的是已经实例化，但还没初始化的状态。而构造器是完成实例化的东东，所以构造器的循环依赖无法解决~~~
 *
 *2、field属性注入（setter方法注入）循环依赖
 * @Service
 * public class A {
 *     @Autowired
 *     private B b;
 * }
 *
 * @Service
 * public class B {
 *     @Autowired
 *     private A a;
 * }
 *
 * 3、prototype field属性注入循环依赖 启动不会异常因为 protoype 用到才会初始化，异常
 *
 * @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
 * @Service
 * public class A {
 *     @Autowired
 *     private B b;
 * }
 *
 * @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
 * @Service
 * public class B {
 *     @Autowired
 *     private A a;
 * }
 *
 * 有关网上@Lazy 解决办法 是错误的 解决不了问题的(可能会掩盖问题)，@Lazy只是延迟初始化而已，当你真正使用到它（初始化）的时候，依旧会报如上异常。
 *
 * Bean的创建核心三个方法
 * createBeanInstance：例化，其实也就是调用对象的构造方法实例化对象
 * populateBean：填充属性，这一步主要是对bean的依赖属性进行注入(@Autowired)
 * initializeBean：回到一些形如initMethod、InitializingBean等方法
 *
 *
 * 	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256); //一级缓存 从该缓存中取出的 bean 可以直接使用 就是最后的实例化对象
 * 	private final Map<String, Object> earlySingletonObjects = new HashMap<>(16); // 早期单例对象 二级缓存
 * 	private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16); // 三级缓存
 *  //Bean开始创建时放值，创建完成时会将其移出~
 * 	private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));
 *  //当这个Bean被创建完成后，会标记为这个 注意：这里是set集合 不会重复
 * 	// 至少被创建了一次的  都会放进这里~~~~
 *	private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<>(256));
 *
 *  {@link org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, boolean)}
 * 先从一级缓存singletonObjects中去获取。（如果获取到就直接return）
 * 如果获取不到或者对象正在创建中（isSingletonCurrentlyInCreation()），那就再从二级缓存earlySingletonObjects中获取。（如果获取到就直接return）
 * 如果还是获取不到，且允许singletonFactories（allowEarlyReference=true）通过getObject()获取。就从三级缓存singletonFactory.getObject()获取。（如果获取到了就从singletonFactories中移除，并且放进earlySingletonObjects。其实也就是从三级缓存移动（是剪切、不是复制哦~）到了二级缓存）
 *
 *
 *
 * 此处说一下二级缓存earlySingletonObjects它里面的数据什么时候添加什么移除？？?
 *
 * 添加：向里面添加数据只有一个地方，就是上面说的getSingleton()里从三级缓存里挪过来
 * 移除：addSingleton、addSingletonFactory、removeSingleton从语义中可以看出添加单例、添加单例工厂ObjectFactory的时候都会删除二级缓存里面对应的缓存值，是互斥的
 *
 *
 * date: 2020/12/7 11:17
 * author: zyc
 */
public class CreateBeanExample {

    @Test
    public void cacheThree(){
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(AppConfig.class);
        Object a = c.getBean("a");
        System.out.println(a);
    }
}
