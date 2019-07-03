package cn.zyc.commandpattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 17:58
 * Version ：1.0
 * Description:
 */
public class SellStock implements Order {
    private Stock sellStock;

    public SellStock(Stock sellStock) {
        this.sellStock = sellStock;
    }

    public void excute() {
        sellStock.sell();
    }

}
