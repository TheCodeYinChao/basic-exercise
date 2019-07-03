package cn.zyc.template.bak.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public abstract class Game {
    abstract void init();
    abstract void start();
    abstract void end();
    void play(){
        init();
        start();
        end();
    }
}
