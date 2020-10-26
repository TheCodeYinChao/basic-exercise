package com.cache.sevice;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 击穿  缓存失效导致迸发访问全落到库上  缓存中没有但数据库中有的数据  加锁（需要双层判断，提升性能）
 * <p>
 * <p>
 * 穿透  访问空数据（缓存 数据库都没有数据）  1 缓存空对象  2 布隆过滤器（ a只增不删 b存在误判 c 不是分布式  【分布式用redis 布隆过滤器】）
 * <p>
 * <p>
 * 雪崩  范围内大量失效  指缓存中数据大批量到过期时间，而查询数据量巨大  1 value 缓存有效时间 ，业务判断问题
 */
@Service
public class CacheService {

    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.001);//hash 长度  精度误差

    @Autowired
    private RedisTemplate redisTemplate;

    //穿透
    public Object queryOrder(String orderId) {
        String rs = "";
        if (!bloomFilter.mightContain(orderId)) {
            rs = (String) redisTemplate.opsForValue().get(orderId);
            if (rs == null) {
                rs = "我是 查询  结果";
                System.out.println(String.format("查询 数据库中 ...... Result %s", rs));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisTemplate.opsForValue().set(orderId, rs);
            }
            System.out.println("查询结果:" + rs);


        } else
            rs = "为查询到结果";
        return rs;
    }


    //击穿
    public Object queryOrder1(String orderId) {
        String rs = (String) redisTemplate.opsForValue().get(orderId);
        if (rs == null) {
            synchronized (this) { //双重检测
                rs = (String) redisTemplate.opsForValue().get(orderId);
                if (rs != null) {
                    return rs;
                }
                rs = "我是 查询  结果";
                System.out.println(String.format("查询 数据库中 ...... Result %s", rs));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisTemplate.opsForValue().set(orderId, rs);
            }
        }
        System.out.println("查询结果:" + rs);
        return rs;
    }


    //雪崩
    public Object queryOrder2(String orderId) {
        String rs = (String) redisTemplate.opsForValue().get(orderId);
        if (rs == null) {
            synchronized (this) { //双重检测
                rs = (String) redisTemplate.opsForValue().get(orderId);

                //解析rs 值  防止范围内大面积 缓存失效
                JSONPObject jsonpObject = new JSONPObject(rs, Map.class);
                Date expire = (Date) jsonpObject.getValue();
                Date date = new Date();
                if (expire.compareTo(date) > 0 & rs != null) {
                    return rs;
                }

                rs = "我是 查询  结果";
                System.out.println(String.format("查询 数据库中 ...... Result %s", rs));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisTemplate.opsForValue().set(orderId, rs);
                redisTemplate.expire(orderId, 1000, TimeUnit.SECONDS);
            }
        }
        System.out.println("查询结果:" + rs);
        return rs;
    }
}
