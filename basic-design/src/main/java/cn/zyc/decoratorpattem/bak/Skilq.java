package cn.zyc.decoratorpattem.bak;

/**
 * @author zyc
 * @date 2019/2/27 13:46
 * @Description:
 */
public class Skilq extends Skills {
    public Skilq(Hero hero) {
        super(hero);
    }

    @Override
    public void leanSkill() {
        System.out.println("学习q技能");
        super.leanSkill();
    }
}
