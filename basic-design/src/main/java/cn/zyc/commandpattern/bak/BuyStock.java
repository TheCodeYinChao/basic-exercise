package cn.zyc.commandpattern.bak;


/**
 * Created by Admin on 2018/6/3.
 */
public class BuyStock implements Order {
    private Strock stock;

    public BuyStock(Strock stock) {
        this.stock = stock;
    }

    public void execu() {
        stock.buy();
    }
}
