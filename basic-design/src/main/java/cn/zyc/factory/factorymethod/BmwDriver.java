package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:12
 * Version ：1.0
 * Description:
 */
public class BmwDriver extends Driver {
    public Car createCar(String car) throws Exception {
        BmwCar bmwCar = new BmwCar();
        bmwCar.setName(car);
        return bmwCar;
    }
}
