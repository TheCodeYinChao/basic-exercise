package cn.zyc.adapterpattem.bak;

/**
 * @author zyc
 * @date 2019/2/26 11:33
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Man m = new Ad(new CarI());
        m.zou();
    }
}
