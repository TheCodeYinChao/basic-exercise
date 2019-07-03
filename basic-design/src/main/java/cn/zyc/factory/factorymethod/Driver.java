package cn.zyc.factory.factorymethod;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 21:11
 * Version ：1.0
 * Description:
 */
abstract class  Driver {
    public abstract Car createCar(String car) throws Exception;
}
