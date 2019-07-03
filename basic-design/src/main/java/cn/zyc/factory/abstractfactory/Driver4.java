package cn.zyc.factory.abstractfactory;

import java.io.InputStream;

/**
 * User: zyc
 * Date: 2018-05-17
 * Time: 10:28
 * Version ：1.0
 * Description:
 */
public class Driver4 {

    public static BmwCar createBmwCar(String car) throws Exception {
        return (BmwCar) Class.forName(car).newInstance();
    }

    public static AudiCar createAudiCar(String car) throws Exception {
        return (AudiCar) Class.forName(car).newInstance();
    }
}
