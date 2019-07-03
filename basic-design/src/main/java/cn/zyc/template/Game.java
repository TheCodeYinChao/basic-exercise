package cn.zyc.template;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 18:49
 * Version ：1.0
 * Description:
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}
