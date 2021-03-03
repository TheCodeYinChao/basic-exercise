package cn.zyc.结构算法之美.link;


/**
 * dsc: 单链表翻转
 * date: 2021/3/3 15:02
 * author: zyc
 */
public class 单链表翻转 {
    Node init(){
        Node n = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        n.next = n1;
        n1.next = n2;
        n2.next = n3;
        return n;
    }

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


    //原地翻转
    Node reverse(Node first){
        Node n = new Node(-1);
        n.next = first;
        Node n1 = n.next;
        Node n2 = n1.next;

        while (n2 != null){
            n1.next = n2.next;
            n2.next = n.next;//关键步骤
            n.next = n2;
            n2 = n1.next;//关键步骤
        }
        return n.next;
    }


    //新头翻转
    Node reverse1(Node first){
        Node n = new Node(-1);
        Node next = first;
        while (next != null){
            Node next1 = next.next;
            next.next = n.next;
            n.next = next;
            next = next1;
        }
        return n.next;
    }




    public static void main(String[] args) {
        单链表翻转 link = new 单链表翻转();
        Node init = link.init();

        link.pint(init);

     /*   System.out.println();
        System.out.println("------------");
        link.pint(link.reverse(init));*/

        System.out.println();
        System.out.println("------------");
        link.pint(link.reverse1(init));
    }
}
