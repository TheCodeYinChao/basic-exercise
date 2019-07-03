package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:58
 * Version ：1.0
 * Description:
 */
public abstract  class LiftState {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    abstract  void open();
    abstract void close();
    abstract void run();
    abstract void stop();
}
