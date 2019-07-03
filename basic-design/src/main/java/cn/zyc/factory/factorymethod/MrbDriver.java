package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:13
 * Version ：1.0
 * Description:
 */
public class MrbDriver extends Driver {
    public Car createCar(String car) throws Exception {
        MrbCar mrbCar = new MrbCar();
        mrbCar.setName(car);
        return mrbCar;
    }
}
