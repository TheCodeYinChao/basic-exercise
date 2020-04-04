package cn.zyc.notsafe;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 *
 *
 *
 */
public class MapDemo {
    @Test
    public void iterator(){
        List list = new ArrayList();
        for(int i = 0 ;i<20 ;i++){
         list.add(i);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
