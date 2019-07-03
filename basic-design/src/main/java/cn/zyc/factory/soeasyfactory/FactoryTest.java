package cn.zyc.factory.soeasyfactory;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:59
 * Version ：1.0
 * Description:
 */
public class FactoryTest {
    public static void main(String[] args) {
        Car bmw = CarFactory.createCar("bmw");
        bmw.drive();
    }
}
