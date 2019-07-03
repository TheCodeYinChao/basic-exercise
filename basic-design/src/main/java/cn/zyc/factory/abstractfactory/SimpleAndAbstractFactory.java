package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:29
 * Version ：1.0
 * Description:
 */
public class SimpleAndAbstractFactory {
    public static void main(String[] args) throws Exception {
        AudiCar car = Driver4.createAudiCar("cn.zyc.factory.abstractfactory.AudiSportCar");
        car.driver();
    }
}
