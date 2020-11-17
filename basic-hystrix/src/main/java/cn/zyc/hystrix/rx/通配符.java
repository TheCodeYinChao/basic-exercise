package cn.zyc.hystrix.rx;

import java.util.List;

/**
 * dsc: 通配符
 * date: 2020/11/17 17:07
 * author: zyc
 */
public class 通配符 {

    /**
     * 如果你想从一个数据类型里获取数据，使用 ? extends 通配符（能取不能存）
     * 如果你想把对象写入一个数据结构里，使用 ? super 通配符（能存不能取）
     * 如果你既想存，又想取，那就别用通配符。
     *
     * 经常发现有List<? super T>、Set<? extends T>的声明，是什么意思呢？
     * <? super T>表示包括T在内的任何T的父类，<? extends T>表示包括T在内的任何T的子类
     */

    public List<? extends Test> get(){
        return null;
    }


    public List<? extends Test> put(List<?super Test> objects){
        return null;
    }


    public class  Test{

    }
}
