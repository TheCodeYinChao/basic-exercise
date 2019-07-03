package cn.zyc.skill.chained.builder;

/**
 * User: zyc
 * Date: 2018-05-31
 * Time: 15:04
 * Version ：1.0
 * Description:
 */
public class User {
    private int age;
    private String name;
    private String sex;

    public User(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public static UserBuilder builder(){
        return  new UserBuilder();
    }

    static  class UserBuilder{
        private int age;
        private String name;
        private String sex;

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public User bulid(){
            return new User(age,name,sex);
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return sex;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
