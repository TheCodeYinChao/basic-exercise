package cn.zyc.decoratorpattem;

/**
 * User: zyc
 * Date: 2018-05-28
 * Time: 15:15
 * Version ：1.0
 * Description:
 */
public class Skill_Q extends Skills{
    private String skillName;

    public Skill_Q(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能Q:" +skillName);
        super.learnSkills();
    }
}
