package cn.zyc.strategypattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 20:10
 * Version ：1.0
 * Description:
 */
public class StrategyPatternTest {
    public static void main(String[] args) {
        OperationAdd operationAdd = new OperationAdd();
        Context context = new Context(operationAdd);
        int i = context.executeStrategy(1, 2);
        System.out.println(i);
    }
}
