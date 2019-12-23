package com.zyc.mybaties;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 */
public class TestSqlSession {

    @Test
    public void testSqlSession()throws Exception{
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
}
