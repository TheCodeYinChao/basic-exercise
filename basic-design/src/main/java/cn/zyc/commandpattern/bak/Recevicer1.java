package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2019/9/8.
 */
public class Recevicer1 implements Recevicer {
    @Override
    public void execute() {
        System.out.println("实际命令接收者 关灯");
    }
}
