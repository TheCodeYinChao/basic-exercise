package cn.zyc.observer.bak;

/**
 * @author zyc
 * @date 2019/2/26 15:00
 * @Description:
 */
public interface Subject {

    public void register(Observer o);

    public void remove(Observer o);

    public void nitify(String o);
}
