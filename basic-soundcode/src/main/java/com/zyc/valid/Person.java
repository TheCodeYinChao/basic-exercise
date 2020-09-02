package com.zyc.valid;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * description: Person <br>
 * date: 2020/9/1 17:51 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Person {

    @NotNull
    public String name;
    @NotNull
    @Min(0)
    public Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
