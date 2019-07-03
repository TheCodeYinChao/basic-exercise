package cn.zyc.facadepattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 10:57
 * Version ：1.0
 * Description:
 */
public class UseMain {
    public static void main(String[] args) {
        ShapeMaker sm = new ShapeMaker();
        sm.circle();
        sm.rectangle();
        sm.square();
    }
}
