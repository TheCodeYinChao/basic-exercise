package cn.zyc.skill.chained.builder.bak;

/**
 * @author zyc
 * @date 2018/6/19 19:10
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        User er = User.UserBulider.instance().setAge("1").setName("er").bulid();
        System.out.println(er.toString());
    }
}
