package cn.zyc.结构算法之美.link;

/**
 * dsc: 中间节点
 * date: 2021/3/3 17:08
 * author: zyc
 */
public class 中间节点 {
    Node init(){
        Node n = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        n.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        return n;
    }

    /**
     * 快慢指针找中间节点
     * @param first
     * @return
     */
    Node midsideNode(Node first){
        Node slow = first;
        Node fast = first;
        while (fast.next != null && fast.next.next !=null){//注意这里的判断是相同的一个 fast.next==null 奇数
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        中间节点 n = new 中间节点();
        Node init = n.init();
        System.out.println(n.midsideNode(init));
    }

}
