package cn.zyc.commandpattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 18:02
 * Version ：1.0
 * Description:
 */
public class UseTest {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
