package cn.zyc.strategypattern;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 20:08
 * Version ：1.0
 * Description:
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public int executeStrategy(int num1,int num2){
        return strategy.doOperatiom(num1,num2);
    }

}
