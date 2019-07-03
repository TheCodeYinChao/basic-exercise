package cn.zyc.commandpattern.bak;

/**
 * @author zyc
 * @date 2019/2/26 16:20
 * @Description:
 */
public class Buy implements Cammand {
    private Recevice recevice;

    public Buy(Recevice recevice) {
        this.recevice = recevice;
    }

    @Override
    public void execute() {
        recevice.buy();
    }
}
