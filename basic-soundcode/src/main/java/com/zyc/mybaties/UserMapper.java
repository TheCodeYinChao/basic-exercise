package com.zyc.mybaties;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 */
@CacheNamespace
public interface UserMapper {
    @Select("select * from user where id =#{id}")
    @Options(useCache = true)//这个默认就是true
    List<User> selectList(@Value("id")Integer id);
}
