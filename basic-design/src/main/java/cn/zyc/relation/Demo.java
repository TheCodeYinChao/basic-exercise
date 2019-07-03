package cn.zyc.relation;

/**
 * @author zyc
 * @date 2019/2/27 10:54
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        Context context = new Context();
        Stat stat = new StatA();
        stat.setContext(context);
        context.get();

    }
}
