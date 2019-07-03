package cn.zyc.commandpattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 17:55
 * Version ：1.0
 * Description:
 */
public class BuyStock implements Order {
    private Stock stock;
    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    public void excute() {
        stock.buy();
    }
}
