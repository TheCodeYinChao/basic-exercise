package cn.zyc.结构算法之美.link;

/**
 * dsc: MergeLinkSorted
 * date: 2021/3/3 19:05
 * author: zyc
 * 两有序链表合并
 */
public class MergeLinkSorted {

    //1->2->4, 1->3->4
    public Node mergeTwoLists(Node l1, Node l2) {
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        Node n = new Node(-1);

        Node head = n;

        while (l1 != null && l2 != null){
            if(l1.val >= l2.val){
                head.next =l2;
                l2 = l2.next;
            }else {
                head.next =l1;
                l1 = l1.next;
            }
            head = head.next;
        }

        head.next = (l1 == null) ? l2 : l1;
        return n.next;
    }
}
