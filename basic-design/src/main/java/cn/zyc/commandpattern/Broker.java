package cn.zyc.commandpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zyc
 * Date: 2018-05-18
 * Time: 18:01
 * Version ：1.0
 * Description:
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.excute();
        }
        orderList.clear();
    }
}
