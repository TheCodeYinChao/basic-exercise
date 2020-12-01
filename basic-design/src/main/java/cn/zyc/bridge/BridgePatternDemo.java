package cn.zyc.bridge;


/**
 * dsc: BridgePatternDemo
 * date: 2020/12/1 12:40
 * author: zyc
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Crile( new GreenDraw(),100, 10);

        redCircle.draw();
    }
}
