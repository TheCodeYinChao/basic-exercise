package cn.zyc.array;

import org.junit.Test;

/**
 * description: Recursion
 * 　递归必须要有三个要素：
 * 　　①、边界条件
 * 　　②、递归前进段
 * 　　③、递归返回段
 * 　　当边界条件不满足时，递归前进；当边界条件满足时，递归返回。
 *
 * 练习:
 * 求一个数的阶乘：n!
 * 递归的二分查找
 * 分治算法
 * 汉诺塔问题
 * 这个单纯思考并无法找到一个有效的循环条件， 这里我们需要变换思维，抽象三个角色  from柱子 中转柱子 to 柱子 ，移动多次你会发现
 * 这其中的规律，只不过ABC 三根柱子一直在变换身份
 *
 * date: 2020/11/2 17:14 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Recursion {

    @Test
    public void mov1e(){

        move(5,"A","B","C");
    }
    public static void move(int dish,String from,String temp,String to){
        if(dish == 1){
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
        }else{
            move(dish-1,from,to,temp);//A为初始塔座，B为目标塔座，C为中介塔座
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
            move(dish-1,temp,from,to);//B为初始塔座，C为目标塔座，A为中介塔座
        }
    }
}
