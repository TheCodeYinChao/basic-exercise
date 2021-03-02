package cn.zyc.结构算法之美.link;

/**
 * dsc: Singlelinkedlistflipping
 * date: 2021/3/2 18:59
 * author: zyc
 */
public class Singlelinkedlistflipping {

    // 1.就地反转法 很强
    public ListNode reverseList1(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode n = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        n.next =n1;
        n1.next =n2;
        n2.next = n3;
        System.out.println(n);
        re(n);
        System.out.println(n);

    }
    public static void re(ListNode  ln){
        //声明哨兵节点
        ListNode l = new ListNode(-1);
        l.next = ln;
        ListNode a1 = l.next;
        ListNode a2 = a1.next;
        while (a2 != null){
            a1.next = a2.next;
            a2.next = l.next;
            l.next = a2;
            a2 = a1.next;
        }
        ln = l.next;
    }
}
