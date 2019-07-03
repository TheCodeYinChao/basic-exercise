package cn.zyc.strategypattern.bak;

/**
 * @author zyc
 * @date 2018/9/25 16:07
 * @Description:
 */
public class Add implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a+b;
    }
}
