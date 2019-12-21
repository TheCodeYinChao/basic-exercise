package cn.zyc.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * 前提：任何一个即将插入的新结点的初始颜色都为红色
 * <p>
 * 1、节点是红色或者黑色
 * 2、根节点是黑色
 * 3、每个最后叶子节点的两个子节点都是黑色空节点
 * 4、每个红色节点的两个子节点都是黑色节点（左旋右旋）
 * 5、从任意节点到其每个叶子节点的所有路径包含相同的黑色节点
 */

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<String, Object> map = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i + "tree", "treeVlaue" + i);
        }

        System.out.println(map);
    }
}
