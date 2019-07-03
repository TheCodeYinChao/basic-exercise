package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:25
 * Version ：1.0
 * Description:
 */
public class BossAbstractFactory {
    public static void main(String[] args) throws Exception {
        Driver3 d = new BusnessDriver();
        AudiCar car = d.createAudiCar("");
        car.driver();
    }
}
