package cn.zyc.observer.bak;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyc
 * @date 2019/2/26 15:00
 * @Description:
 */
public class Subject1 implements Subject {
    private List<Observer> os = new ArrayList<>();
    @Override
    public void register(Observer o) {
        os.add(o);
    }

    @Override
    public void remove(Observer o) {
        os.remove(o);
    }

    @Override
    public void nitify(String msg) {
       for(Observer o:os){
            o.recevice(msg);
       };
    }
}
