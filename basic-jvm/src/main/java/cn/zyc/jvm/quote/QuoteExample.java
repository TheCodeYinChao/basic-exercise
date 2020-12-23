package cn.zyc.jvm.quote;

import org.junit.Test;

/**
 * 引用传递修改会导致原地址值修改
 * dsc: QuoteExample
 * date: 2020/12/7 14:18
 * author: zyc
 */
public class QuoteExample {
    @Test
    public void a(){
        User user = new User();
        user.setName("1111");
        user.setPassword("2222");
        b(user);
        System.out.println(user);
    }


    public void b(User user){
        User u = user;

        System.out.println(user);
        u.setName("aaa");
        u.setPassword("cdcccc");

        System.out.println(u);
        System.out.println(user);
    }

}
