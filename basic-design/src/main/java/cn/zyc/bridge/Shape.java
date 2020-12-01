package cn.zyc.bridge;

/**
 * dsc: Shape 形状
 * date: 2020/12/1 12:33
 * author: zyc
 */
public abstract class Shape {
    protected DrawApi drawApi;
    protected int x;
    protected int y;
    abstract void draw();


    public Shape(DrawApi drawApi, int x, int y) {
        this.drawApi = drawApi;
        this.x = x;
        this.y = y;
    }
}
