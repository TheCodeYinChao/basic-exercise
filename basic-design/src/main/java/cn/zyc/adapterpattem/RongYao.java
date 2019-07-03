package cn.zyc.adapterpattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:13
 * Version ：1.0
 * Description:
 */
public class RongYao {
    TwoPinSoket twoPinSoket;

    public RongYao() {}

    public void setTwoPinSoket(TwoPinSoket twoPinSoket)
    {
        this.twoPinSoket = twoPinSoket;
    }

    public void chargeRequest()
    {
        System.out.println("华为荣耀手机， " + twoPinSoket.voltage() + " 伏特充电中\n");
    }
}
