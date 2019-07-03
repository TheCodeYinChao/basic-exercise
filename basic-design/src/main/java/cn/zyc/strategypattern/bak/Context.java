package cn.zyc.strategypattern.bak;

/**
 * @author zyc
 * @date 2018/9/25 16:08
 * @Description:
 */
public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int execute(int a, int b){
        return strategy.execute(a,b);
    }
}
