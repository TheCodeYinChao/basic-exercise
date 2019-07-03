package cn.zyc.adapterpattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:10
 * Version ：1.0
 * Description:
 */
public class ThreePinSoketAmerica implements ThreePinSoket {
    public void chargeWithThreePin() {
        System.out.println("美国标准三孔插坐");
    }

    public int voltage() {
        return 110;
    }
}
