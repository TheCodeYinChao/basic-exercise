package cn.zyc.observer;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:05
 * Version ：1.0
 * Description:
 */
public class Subject1 implements Subject {
    private List<Observer> list = new ArrayList<Observer>();
    private String msg;

    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    public void removeObServer(Observer observer) {
        int i = list.indexOf(observer);
        if(i >= 0){
            list.remove(i);
        }
    }

    public void notifyObServer() {
        for(Observer s : list){
            s.update(msg);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObServer();
    }
}
