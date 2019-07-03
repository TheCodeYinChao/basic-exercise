package cn.zyc.commandpattern.bak;

/**
 * @author zyc
 * @date 2019/2/26 16:19
 * @Description:
 */
public class Sell implements Cammand {
    private Recevice recevice;

    public Sell(Recevice recevice) {
        this.recevice = recevice;
    }

    @Override
    public void execute() {
        recevice.sell();
    }
}
