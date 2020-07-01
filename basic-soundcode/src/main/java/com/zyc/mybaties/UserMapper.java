package com.zyc.mybaties;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resources;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 */
public interface UserMapper {
    @Select("select * from user where id =#{id}")
    List<User> selectList(@Value("id")Integer id);
}
