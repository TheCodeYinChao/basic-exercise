package cn.zyc.observer.bak.bak;

/**
 * Created by Admin on 2018/6/3.
 */
public class Subject1 extends   Subject {
    void re(Observer observer) {
        observers.add(observer);
    }

    void rm(Observer observer) {
        int i = observers.indexOf(observer);
        if(i >= 0){
            observers.remove(i);
        }
    }

    void ny(String msg) {
        for (Observer observer: observers)
        observer.update(msg);
    }
}
