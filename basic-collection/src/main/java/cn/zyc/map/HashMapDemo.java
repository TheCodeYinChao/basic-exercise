package cn.zyc.map;

import java.util.HashMap;

/**
 * Q HashMap 有什么特点，存在的意义是什么？
 * A  特点：k-v 结构、k具有唯一性 、接受空键,空值 、速度快
 * 意义：快速的查找并且插入、删除性能都不错的一种 K/V（key/value）数据结构
 * <p>
 * Q 底层由什么组成，为什么？
 * <p>
 * A 数组+链表（单项链表）   数组+红黑树
 * 数组（查询快）、链表（插入快）、红黑树（查询速度快，增删慢时间维度O（n））
 * <p>
 * <p>
 * Q 各组成成分间是如何变化和协同的？
 * <p>
 * A 默认数组16 加载因子0.75（扩容阀值） 最大容积2^32次方
 * <p>
 * 链表超过8之后链表---》红黑树 红黑树内数量减到6时---》恢复成链表
 * 元素和链表长度（MIN_TREEIFY_CAPACITY = 64）结合来决定是扩容还是树化
 * <p>
 * Q HashMap 的使用场景是什么？
 * A
 * <p>
 * Q HashMap 有什么缺点，如何进行改进？
 * <p>
 * A 操作度复杂，且是线程非安全的，可以使用CurrentHashMap
 * <p>
 * Q get 描述？
 * A 先根据hash|key 确定位于数组的那个位置,再通过key 哈希值和key== equal 来判断
 * 对比查询第二个节点类型确定是红黑树还是链表
 * 剩下的就是遍历链表或红黑树取数据
 * Q put 描述？
 * A 1容器分配
 * 2判断hash是否在某个数组位置，没有则新建节点hash位
 * 3 有值判断数组位置的第一个节点是否有相同（hash equals）
 * 4 不同 则判断当前节点的类型 是否是红黑树 正确则存入红黑树节点
 * 5 不同则为链表 循环链表 查找下一个链表节点，为空则新增加入next加点 如果元素大于=于8-1 变树操作（如果数组还很小则扩容 MIN_TREEIFY_CAPACITY 64）循环操作变树结构
 * 6 如果已经存在则直接替换
 * 7 记录操作数
 * 8 数量大于16*0.75 则扩容.
 * <p>
 * Q remove 描述？
 * A 树较小时自动变为链表，此时并未根据是否为6来判断是否变为链表，6的判断再对容器操作时进行判断
 * <p>
 * Q resize()是如何操作的？
 */


public class HashMapDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.put("1", null);
        map.put(null, "");
        map.put("3", "");
        System.out.println(map);

        Object o = map.get("3");

        map.remove("3");
        System.out.println(o);
    }
}
