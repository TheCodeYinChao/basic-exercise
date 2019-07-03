package cn.zyc.decoratorpattem;

/**
 * User: zyc
 * Date: 2018-05-28
 * Time: 15:13
 * Version ：1.0
 * Description:
 */
public class Skills implements Hero {
    private Hero hero;

    public Skills(Hero hero) {
        this.hero = hero;
    }

    public void learnSkills() {
        hero.learnSkills();
    }
}
