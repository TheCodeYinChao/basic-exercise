package com.zyc.bitopera;

public class Bit {

    /**
     * hash存储分布
     *
     * 节点数量变更时hash 出来的值也会有所变换
     *
     * 为此引入hash一致性原理
     * 同时为了保证节点的分布均匀，我们会虚拟出很多个节点
     *
     * 对于容器相关用到很多hash算法 并且保证了hash算法的均匀散布
     * @param args
     */
    public static void main(String[] args) {
        int hash = spread("1212121".hashCode());

        System.out.println(hash);

        int i = 16 & hash;

        System.out.println(i);


    }
    static final int spread(int h) {
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }
}
