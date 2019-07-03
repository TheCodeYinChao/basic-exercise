package cn.zyc.commandpattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 17:52
 * Version ：1.0
 * Description:
 */
public class Stock {
    private String name = "abc";
    private int quantity = 10;

    void buy(){
        System.out.println("[stock buy]"+name+quantity);
    }
    void sell(){
        System.out.println("[stock sell]"+name+quantity);
    }
}
