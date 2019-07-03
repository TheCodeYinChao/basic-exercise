package cn.zyc.factory.abstractfactory;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:21
 * Version ：1.0
 * Description: 具体工厂
 */
public class SportDriver extends Driver3  {
    public BmwCar createBmwCar(String car) throws Exception {
        return new BmwSportCar() ;
    }

    public AudiCar createAudiCar(String car) throws Exception {
        return new AudiSportCar();
    }
}
