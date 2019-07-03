package cn.zyc.factory.soeasyfactory;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:55
 * Version ：1.0
 * Description:
 */
public class CarFactory {

    public static Car createCar(String name){
        Car car = null;
        if(name.equals("bmw")) {
            car = new BmwCar();
        }
        if(name.equals("mrb")){
            car = new MrbCar();
        }
        return  car;
    }
}
