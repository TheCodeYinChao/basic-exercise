package cn.zyc.observer.bak.bak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/6/3.
 */
public abstract class Subject {
    public List<Observer> observers = new ArrayList();
    abstract void re(Observer observer);
    abstract void rm(Observer observer);
    abstract void ny(String msg);
}
