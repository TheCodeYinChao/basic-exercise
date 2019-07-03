package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class SellStock implements Order {
    private Strock strock;

    public SellStock(Strock strock) {
        this.strock = strock;
    }

    public void execu() {
        strock.sell();
    }
}
