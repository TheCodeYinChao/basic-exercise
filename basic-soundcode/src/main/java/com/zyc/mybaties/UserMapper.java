package com.zyc.mybaties;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2019/12/22.
 */
public interface UserMapper {
    @Select("select * from user")
    List<Map> selectList();
}
