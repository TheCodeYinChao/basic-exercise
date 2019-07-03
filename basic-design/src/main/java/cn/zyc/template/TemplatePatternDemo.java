package cn.zyc.template;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 18:52
 * Version ：1.0
 * Description:
 */
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        game = new Football();
        game.play();

    }
}
