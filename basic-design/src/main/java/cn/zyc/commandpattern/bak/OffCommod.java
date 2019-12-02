package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2019/9/8.
 */
public class OffCommod implements Commod {
    private Recevicer recevicer;

    public OffCommod(Recevicer recevicer) {
        this.recevicer = recevicer;
    }
    @Override
    public void execute() {
        recevicer.execute();
    }
}
