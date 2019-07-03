package cn.zyc.relation;

/**
 * @author zyc
 * @date 2019/2/27 10:52
 * @Description:
 */
public abstract  class Stat {
    private Context context;

    public void setContext(Context context){
        this.context = context;
        context.setStat(this);
    }

    public abstract void sys();
}
