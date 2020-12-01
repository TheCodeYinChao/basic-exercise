package cn.zyc.bridge;

/**
 * dsc: Crile
 * date: 2020/12/1 12:35
 * author: zyc
 */
public class Crile extends Shape {
    public Crile(DrawApi drawApi, int x, int y) {
        super(drawApi, x, y);
    }

    public Crile() {
        super(null, 0, 0);
    }

    @Override
    void draw() {
        System.out.println("x:"+x+ "-" +"y:"+y);
        drawApi.drawCirle();
    }
}
