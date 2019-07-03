package cn.zyc.beans.reflect;

import cn.zyc.beans.reflect.annotation.Demo;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 13:59
 * Version ：1.0
 * Description:
 */
public class User {
    private String name;
//    private Integer age;
//    private Boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


  /*  public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
