package com.zyc.mybaties;


import org.apache.ibatis.cache.decorators.BlockingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.apache.ibatis.cache.decorators.WeakCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 */

//@MapperScan
public class TestSqlSession {

    @Test
    public void testSqlSession()throws Exception{

//        LogFactory

        //二级缓存 sqlsession
        InputStream inputStream = Resources.getResourceAsStream("mybaties.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);
        build.getConfiguration().addInterceptor(new DemoPlugin());
        SqlSession sqlSession = build.openSession();
        sqlSession.getConfiguration().addMapper(UserMapper.class);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<Map> rs = mapper.selectList();
        System.out.println(rs.toString());

    }


    @Test
    public void decoratorpattem(){
        LruCache lruCache = new LruCache(new BlockingCache(new ScheduledCache(new PerpetualCache("1"))));
        lruCache.setSize(1);
        lruCache.putObject("aa","aa");
        lruCache.putObject("a2","aa");
    }


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
}
