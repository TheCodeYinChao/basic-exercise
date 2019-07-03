package cn.zyc.decoratorpattem.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Skill implements Hero {
    private Hero hero;

    public Skill(Hero hero) {
        this.hero = hero;
    }

    public void leanSkill() {
        hero.leanSkill();
    }
}
