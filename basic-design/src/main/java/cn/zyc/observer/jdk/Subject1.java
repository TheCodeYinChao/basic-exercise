package cn.zyc.observer.jdk;


import java.util.Observable;

/**
 * User: zyc
 * Date: 2018-05-16
 * Time: 20:19
 * Version ：1.0
 * Description:
 */
public class Subject1 extends Observable {

    private String msg ;


    public String getMsg()
    {
        return msg;
    }


    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg)
    {
        this.msg = msg  ;
        setChanged();
        notifyObservers();
    }
}
