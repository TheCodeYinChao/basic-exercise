package cn.zyc.strategypattern.bak;

/**
 * @author zyc
 * @date 2018/9/25 16:09
 * @Description:
 */
public class TestStrategy {
    public static void main(String[] args) {
        Strategy strategy = new Add();
        Context context = new Context();
        context.setStrategy(strategy);
        System.out.println(context.execute(1,2));

        context.setStrategy(new Sub());
        System.out.println(context.execute(3,1));

    }
}
