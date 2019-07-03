package cn.zyc.beans.reflect.annotation;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 15:02
 * Version ：1.0
 * Description:
 */
public class User {
    @Demo(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
