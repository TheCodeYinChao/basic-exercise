package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:23
 * Version ：1.0
 * Description: 具体工厂
 */
public class BusnessDriver extends Driver3 {
    public BmwCar createBmwCar(String car) throws Exception {
        return new BmwBusnessCar();
    }

    public AudiCar createAudiCar(String car) throws Exception {
        return new AudiBusnessCar();
    }
}
