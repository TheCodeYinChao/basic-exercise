package cn.zyc.skill.chained.builder.bak;

/**
 * @author zyc
 * @date 2018/6/19 19:03
 * @Description:
 */
public class User {
    private String name;
    private String  age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    static class  UserBulider{
        private String name;
        private String age;

        public static UserBulider instance(){
            return new UserBulider();
        }

        public String getName() {
            return name;
        }

        public UserBulider setName(String name) {
            this.name = name;
            return this;
        }

        public String getAge() {
            return age;
        }

        public UserBulider setAge(String age) {
            this.age = age;
            return this;
        }

        public User bulid(){
            return new User(name,age);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
