package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2019/9/8.
 */
public class OnCommod implements Commod {
    private Recevicer recevicer;

    public OnCommod(Recevicer recevicer) {
        this.recevicer = recevicer;
    }

    @Override
    public void execute() {
        recevicer.execute();
    }
}
