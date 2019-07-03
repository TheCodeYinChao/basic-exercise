package cn.zyc.skill.chained.builder;

/**
 * User: zyc
 * Date: 2018-05-31
 * Time: 15:08
 * Version ：1.0
 * Description:
 */
public class MainTest {
    public static void main(String[] args) {
        User user =  User.builder()
                    .setAge(1)
                    .setName("nihao")
                    .setSex("nihao")
                    .bulid();
        System.out.println(user);
    }
}
