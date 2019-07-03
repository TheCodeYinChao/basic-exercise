package cn.zyc.dynamic.proxy;

/**
 * User: zyc
 * Date: 2018-06-05
 * Time: 13:50
 * Version ：1.0
 * Description:
 */
public class MainTest {
    public static void main(String[] args) {
        Anamle anamle = (Anamle) new ProxyC().newInstance(new Dog());
        anamle.run();

        Flower flower = (Flower)new ProxyC().newInstance(new Rose());
        flower.des();
    }
}
