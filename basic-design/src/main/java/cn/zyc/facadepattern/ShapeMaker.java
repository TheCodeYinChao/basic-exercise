package cn.zyc.facadepattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 10:50
 * Version ：1.0
 * Description:
 */
public class ShapeMaker {
        private Shape square;
        private Shape rectangle;
        private Shape circle;

    public ShapeMaker() {
        this.square = new Square();
        this.rectangle = new Rectangle();
        this.circle = new Circle();
    }

    public  void circle(){
        circle.draw();
    }

    public void rectangle(){
        rectangle.draw();
    }
    public void square(){
        square.draw();
    }

}
