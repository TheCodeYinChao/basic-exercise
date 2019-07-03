package cn.zyc.dynamic.proxy.bak;

/**
 * @author zyc
 * @date 2018/6/19 18:58
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        Flower flower = (Flower)  new ProxyF().instance(new Rose());
        flower.open();
    }
}
