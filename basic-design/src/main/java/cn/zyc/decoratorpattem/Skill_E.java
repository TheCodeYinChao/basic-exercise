package cn.zyc.decoratorpattem;

/**
 * User: zyc
 * Date: 2018-05-28
 * Time: 15:18
 * Version ：1.0
 * Description:
 */
public class Skill_E extends Skills {
    private String name;

    public Skill_E(Hero hero, String name) {
        super(hero);
        this.name = name;
    }

    @Override
    public void learnSkills() {
        System.out.println("EE");
        super.learnSkills();
    }
}
