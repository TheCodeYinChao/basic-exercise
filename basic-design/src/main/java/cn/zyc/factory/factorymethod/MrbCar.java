package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:11
 * Version ：1.0
 * Description:
 */
public class MrbCar extends Car {
    public void driver() {
        System.out.println("["+getName()+"]----------->");
    }
}
