package cn.zyc.adapterpattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:11
 * Version ：1.0
 * Description:
 */
public class TwoPinSoketChina implements  TwoPinSoket {
    public void chargeWithTwoPin() {
        System.out.println("中国两孔插座");
    }

    public int voltage() {
        return 220;
    }
}
