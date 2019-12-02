package cn.zyc.commandpattern.bak;

/**
 * Created by Admin on 2019/9/8.
 */
public class Enter {
    public static void main(String[] args) {

        Recevicer recevicer = new Recevicer1();

        Commod com = new OffCommod(recevicer);
        Invoker invoker = new Invoker();
        invoker.setCommod(com);
        invoker.execute();

    }
}
