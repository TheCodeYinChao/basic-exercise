package cn.zyc.adapterpattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:12
 * Version ：1.0
 * Description:
 */
public class AmericaAdapter implements TwoPinSoket {
    ThreePinSoket tps ;

    public AmericaAdapter(ThreePinSoket tps) {
        this.tps = tps;
    }

    public void chargeWithTwoPin() {
        tps.chargeWithThreePin();
    }

    public int voltage() {
        return tps.voltage()*2;
    }
}
