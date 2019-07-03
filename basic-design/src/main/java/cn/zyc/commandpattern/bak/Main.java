package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Main {
    public static void main(String[] args) {
        Strock strock = new Strock();
        BuyStock buyStock= new BuyStock(strock);
        SellStock sellStock = new SellStock(strock);
        Broker broker = new Broker();
        broker.add(buyStock);
        broker.add(sellStock);
        broker.play();
    }
}
