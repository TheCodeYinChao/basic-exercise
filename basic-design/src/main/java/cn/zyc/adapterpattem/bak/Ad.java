package cn.zyc.adapterpattem.bak;

/**
 * @author zyc
 * @date 2019/2/26 11:32
 * @Description:
 */
public class Ad  implements Man {
    private Car car;

    public Ad(Car car) {
        this.car = car;
    }

    @Override
    public void zou() {
        car.run();
    }
}
