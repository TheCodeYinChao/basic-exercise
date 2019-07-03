package cn.zyc.decoratorpattem;

/**
 * User: zyc
 * Date: 2018-05-28
 * Time: 15:19
 * Version ：1.0
 * Description:
 */
public class MainTest {
    public static void main(String[] args) {
        Hero hr = new BlindMonk();

        Skills hero = new Skill_Q(new Skill_E(new Skill_W(hr,"www"),"eee"),"q技能");


        hero.learnSkills();



    }
}
