package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:15
 * Version ：1.0
 * Description:
 */
public class Boss {
    public static void main(String[] args)throws Exception {
        Driver d = new BmwDriver();
        Car bmw = d.createCar("bmw");
        bmw.driver();
    }
}
