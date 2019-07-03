package cn.zyc.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:21
 * Version ：1.0
 * Description:
 */
public class Observer1 implements Observer{
    public void registerSubject(Observable observable)
    {
        observable.addObserver(this);
    }
    public void update(Observable o, Object arg) {
        if (o instanceof Subject1)
        {
            Subject1 subjectFor3d = (Subject1) o;
            System.out.println("Subject1' msg -- >" + subjectFor3d.getMsg());
        }
    }

}
