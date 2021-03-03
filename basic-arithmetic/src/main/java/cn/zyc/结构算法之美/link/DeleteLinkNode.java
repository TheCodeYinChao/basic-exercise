package cn.zyc.结构算法之美.link;

/**
 * dsc: DeleteLinkNode
 * date: 2021/3/3 17:37
 * author: zyc
 * 删除 链表倒数第n个节点
 */
public class DeleteLinkNode {

    Node init(){
        Node n = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        Node n5 = new Node(6);
        n.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        return n;
    }

    public static void main(String[] args) {
        DeleteLinkNode d = new DeleteLinkNode();
        Node init = d.init();

        Node delete = d.delete1(init, 2);
        d.pint(delete);
    }
    //方案一两遍遍历 这个有点问题没有注意边界问题
    public Node  delete(Node head,int n){
        int count = 0;
        Node ne = head;
        while (ne != null){
            ++count;
            ne = ne.next;
        }
        count = count - n;
        int start = 1;

        Node node =head;
        while (node !=null){
            if(start == count){
                node.next = node.next.next;
                break;
            }
            node = node.next;
            ++start;
        }
        return head;
    }

    //方案一改进两遍遍历带哨兵节点
    public Node  delete1(Node head,int n){
        Node z = new Node(-1);//哨兵节点处理 边界问题
        z.next = head;
        int count = 0;
        Node ne = z;
        while (ne != null){
             count ++;
             ne = ne.next;
        }
        count = count - n;
        int start = 1;

        Node node = z;
        while (node !=null){
            if(start == count){
                node.next = node.next.next;
                break;
            }
            node = node.next;
            ++start;
        }
        return z.next;
    }

    //方案二快慢指针
    public Node  delete2(Node head,int n){
        Node node = new Node(-1);
        node.next = head;
        //快几点先走n步
        Node h = node;
        for (int i = 0; i < n; i++) {
            h = h.next;
        }

        Node slow = node;
        while (h.next!=null){
            slow = slow.next;
            h = h.next;
        }
        slow.next = slow.next.next;
        return node.next;

    }

    //方案三栈 官方案例中使用jdk自带的栈
    //Deque<ListNode> stack = new LinkedList<ListNode>();

    void pint(Node first){
        if(first == null){
            return;
        }
        Node n = new Node(-1);
        n.next = first;
        while (n.next!=null){
            System.out.print(n.next.val+" ");
            n = n.next;
        }
    }

}
