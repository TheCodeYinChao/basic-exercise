package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:14
 * Version ：1.0
 * Description: 抽象产品
 */
public abstract  class AudiCar {
    private String name;

    abstract void driver();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
