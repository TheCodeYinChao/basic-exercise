package cn.zyc.relation;

/**
 * @author zyc
 * @date 2019/2/27 10:53
 * @Description:
 */
public class Context {
    Stat stat;

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public void get(){
        stat.sys();
    }
}
