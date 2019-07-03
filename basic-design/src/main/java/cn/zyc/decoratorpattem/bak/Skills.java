package cn.zyc.decoratorpattem.bak;

/**
 * @author zyc
 * @date 2019/2/27 13:48
 * @Description:
 */
public class Skills implements Hero {
    private Hero hero;

    public Skills(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void leanSkill() {
        hero.leanSkill();
    }
}
