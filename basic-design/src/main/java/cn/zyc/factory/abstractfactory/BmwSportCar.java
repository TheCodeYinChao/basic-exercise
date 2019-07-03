package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:16
 * Version ：1.0
 * Description: 具体产品
 */
public class BmwSportCar extends BmwCar {
    void driver() {
        System.out.println(this.getName()+"----BmwSportCar-----------------------");
    }
}
