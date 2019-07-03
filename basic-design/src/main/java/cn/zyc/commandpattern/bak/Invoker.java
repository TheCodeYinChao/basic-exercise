package cn.zyc.commandpattern.bak;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyc
 * @date 2019/2/26 16:20
 * @Description:
 */
public class Invoker {
    private List<Cammand> cm = new ArrayList<>();

    public void receviceTask(Cammand c){
        cm.add(c);
    }

    public void excute(){
        for(Cammand c:cm){
            c.execute();
        }
        cm.clear();
    }

}
