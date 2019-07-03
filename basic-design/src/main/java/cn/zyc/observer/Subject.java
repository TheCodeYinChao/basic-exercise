package cn.zyc.observer;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:02
 * Version ：1.0
 * Description: 服务
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObServer(Observer observer);

    public void notifyObServer();

}
