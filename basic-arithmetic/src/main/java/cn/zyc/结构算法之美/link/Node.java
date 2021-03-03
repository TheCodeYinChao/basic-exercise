package cn.zyc.结构算法之美.link;

/**
 * dsc: Node
 * date: 2021/3/3 18:21
 * author: zyc
 */
public class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
