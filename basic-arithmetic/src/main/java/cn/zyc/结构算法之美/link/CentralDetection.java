package cn.zyc.结构算法之美.link;

/**
 * @author zyc
 * @date 2021/3/3
 * @time 22:35
 * @description : 中环检测
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 *如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
 * 则链表中存在环。 为了表示给定链表中的环，
 * 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 */
public class CentralDetection {

    //两种题解
    //一种集合
    //一种算数代数的方式来确定指针
    Node detection(Node head){
        int pos = -1;
        Node slow = head;
        Node fast = head;
        boolean is = false;
        while (fast.next != null && fast.next.next!= null){
            ++ pos;
            if(slow == fast){
                is =true;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if(is){
            System.out.println("tail connects to node index "+ pos);
        }
        return slow;
    }

}
