package cn.zyc;

/**
 * description: MethodAreaTest <br>
 * date: 2020/5/18 17:55 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        System.out.println(order.count);
        System.out.println(order.count);

    }
}

class Order {
    public static int count = 1;


    public int aa;


    public static App a = new App();

    static {
        //类加载 耗时操作会阻塞类的加载
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
