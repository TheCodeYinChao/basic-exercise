package cn.zyc.factory.soeasyfactory;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:54
 * Version ：1.0
 * Description: 车基类
 */
abstract class  Car {
    private String name;
    abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
