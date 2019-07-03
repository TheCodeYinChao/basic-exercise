package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:08
 * Version ：1.0
 * Description: 抽象产品
 */
abstract class Car {
    private String name;
    public abstract void driver();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
