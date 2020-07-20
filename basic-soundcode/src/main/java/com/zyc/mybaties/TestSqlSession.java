package com.zyc.mybaties;


import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.BlockingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.apache.ibatis.cache.decorators.WeakCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.CacheBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 *
 * 二级缓存（mapper - namespace） 必须提交之后才会清除 因为有事务  通过暂存区解决
 *
 *
 *  更新 删除 新增 会导致 一级和二级缓存都失效
 */

public class TestSqlSession {
    private static final SqlSessionFactory sqlSessionFactory;
    static {//实例化工厂
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybaties.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        sqlSessionFactory.getConfiguration().addInterceptor(new DemoPlugin());
        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
    }

    /**
     * 测试sql 查询
     * @throws Exception
     */
    @Test
    public void testSqlSession()throws Exception{
        //二级缓存 sqlsession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            List<User> rs = mapper.selectList(1);
            sqlSession.commit();//会话提交后缓存才能生效
            List<User> rs1 = mapper.selectList(1);
            System.out.println(rs.toString());
    }


    /**
     * 缓存装饰模式
     */
    @Test
    public void decoratorpattem(){
        CacheBuilder c = new CacheBuilder("TEST");
        c.clearInterval((long) 6000);//缓存存活时间
        c.readWrite(true);//序列化
        Cache cache = c.build();
        cache.putObject("test","ceshi");
        System.out.println(cache.getObject("test"));
    }


    /**
     * 缓存信息
     */
    @Test
    public void gcTest(){

        WeakCache weakCache = new WeakCache(new PerpetualCache("11"));
        weakCache.putObject("aa","aad");


        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<Object>(); //回收引用队列

        Object o1 = new Object();
        WeakReference<Object> owr = new WeakReference<Object>(o1, objectReferenceQueue);//弱引用对象 当有gc发生时回收
        Object o2 = owr.get();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                WeakReference<Object> poll;
                while ((poll = (WeakReference<Object>)objectReferenceQueue.poll()) !=null){
                    Object o1 = poll.get();
                    System.out.println("o1:"+o1);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
        System.gc();
        Object o = owr.get();
    }

    @Test
    public void logFactory(){
        //单例 适配器  工厂
        System.out.println(LogFactory.class.getName());
        Log log = LogFactory.getLog(StdOutImpl.class);

        log.debug("ddas");
        log.trace("sdds");

        Logger logger = Logger.getLogger(TestSqlSession.class);
        logger.debug("dda");


    }




    @Test
    public  void logger(){
        Logger logger = Logger.getLogger(this.getClass());
        logger.setLevel(Level.DEBUG);
        logger.debug("aa");


    }
}
