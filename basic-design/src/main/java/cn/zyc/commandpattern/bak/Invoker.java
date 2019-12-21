package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2019/9/8.
 * 命令执行者
 */
public class Invoker {
    private Commod commod;

    public void setCommod(Commod commod) {
        this.commod = commod;
    }

    public void execute() {
        commod.execute();
    }
}
