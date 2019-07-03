package cn.zyc.decoratorpattem.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Skill_Q extends Skill {
    public Skill_Q(Hero hero) {
        super(hero);
    }

    @Override
    public void leanSkill() {
        System.out.println("正学习Q技能");
        super.leanSkill();
    }
}
