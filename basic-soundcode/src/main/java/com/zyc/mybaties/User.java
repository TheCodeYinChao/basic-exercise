package com.zyc.mybaties;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2020/6/26.
 */

@Alias("user")
public class User implements Serializable {
    private Integer id;
    private String name;
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
