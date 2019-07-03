package cn.zyc.template;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 18:51
 * Version ：1.0
 * Description:
 */
public class Football extends Game {
    void initialize() {
        System.out.println("[Football]initialize");
    }

    void startPlay() {
        System.out.println("[Football]startPlay");
    }

    void endPlay() {
        System.out.println("[Football][endPlay]");
    }
}
