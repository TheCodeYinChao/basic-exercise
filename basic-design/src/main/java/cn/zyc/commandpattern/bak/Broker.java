package cn.zyc.commandpattern.bak;

import java.util.*;

/**
 * Created by Admin on 2018/6/3.
 */
public class Broker {
    private List<Order> orders = new ArrayList<Order>();

    public void add(Order order){
        orders.add(order);
    }

    public void play(){
        for(Order order:orders){
            order.execu();
        }
        orders.clear();
    }

}
