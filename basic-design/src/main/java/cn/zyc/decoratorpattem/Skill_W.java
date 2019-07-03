package cn.zyc.decoratorpattem;

/**
 * User: zyc
 * Date: 2018-05-28
 * Time: 15:17
 * Version ：1.0
 * Description:
 */
public class Skill_W extends Skills{
    private String name;
    public Skill_W(Hero hero,String name) {
        super(hero);
        this.name = name;
    }

    @Override
    public void learnSkills() {
        System.out.println("w");
        super.learnSkills();
    }
}
