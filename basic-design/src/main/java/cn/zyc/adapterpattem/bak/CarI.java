package cn.zyc.adapterpattem.bak;

/**
 * @author zyc
 * @date 2019/2/26 11:31
 * @Description:
 */
public class CarI implements Car {
    @Override
    public void run() {
        System.out.println("车 载人 飞奔！");
    }
}
