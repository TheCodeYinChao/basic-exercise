package cn.zyc.decoratorpattem.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Main {
    public static void main(String[] args) {
        Hero hero = new BindMonk();

        Skill skill = new Skill(new Skill_Q(hero));
        skill.leanSkill();

    }
}
