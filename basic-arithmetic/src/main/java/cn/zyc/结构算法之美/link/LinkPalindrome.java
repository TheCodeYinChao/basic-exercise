package cn.zyc.结构算法之美.link;

/**
 * dsc: LinkPalindrome 单链表的回文
 * date: 2021/3/2 16:17
 * author: zyc
 */
public class LinkPalindrome {
    Node head;
    /**
     * 1 快慢指针定位中间节点
     * 2 从中间节点对后半部分逆序
     * 3 前后半部分比较，判断是否为回文
     * 4 后半部分逆序复原
     */

   /* public static void main(String[] args) {
        LinkPalindrome a = new LinkPalindrome();
        String str = "AmanaplanacanalPanama1";
        //初始化数据
        char[] chars = str.toLowerCase().toCharArray();
        Node head = a.head;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            Node n = new Node();
            if(i==0){
                n.val = aChar;
                n.next = null;
                a.head = n;
            }
            else {
                head.next = n;
                n.val = aChar;
            }
            head = n;
        }
//        a.printAll();

        System.out.println(isPalindrome(a));

    }*/

    public static boolean  isPalindrome(LinkPalindrome a){
        Node headSlow = a.head;
        Node headFast = a.head;
        if(headSlow.next == null){
            return true;
        }
        while (headFast.next != null && headFast.next.next != null){//next 判断是奇数 ，next.next是偶数
            headSlow = headSlow.next;
            headFast = headFast.next.next;

        }
        System.out.println("中间节点" + headSlow.val);
        System.out.println("开始执行奇数节点的回文判断");
        //后半部分逆序
        Node node1 = null; //翻转后的链表
        if(headFast.next == null){//奇数
            Node node = headSlow.next;
            while (node.next != null){
                node = node.next;
                Node n = new Node();
                n.val = node.val;
                n.next = node1;
                node1 = n;
            }
        }else {//偶数
            Node node = headSlow;
            while (node.next != null){
                node = node.next;
                Node n = new Node();
                n.val = node.val;
                n.next = node1;
                node1 = n;
            }
        }


        //前后比较回文
        Node head = a.head;

        while (head.next !=null && node1.next!=null){
            if (head.val != node1.val) {
                return false;
            }
            head = head.next;
            node1 = node1.next;
        }

        //逆序复原
        return true;
    }

    static class Node{
        char val;
        Node next;

        @Override
        public String toString() {
            return val+"";
        }
    }

    void printAll(){
        Node head = this.head;
        if(head.next == null){
            System.out.print(head.val); return;
        }
        while (head.next != null){
            head = head.next;
            System.out.print(head.val);
        }
    }


    public static void main(String[] args) {
        init();
    }
    public static void init(){
        Node n = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();

        n.val= 'a';
        n1.val='b';
        n2.val='c';
        n3.val='d';

        n.next =n1;
        n1.next = n2;
        n2.next = n3;
        Node re = re(n);
        System.out.println(re);
    }

    public static Node re(Node headSlow){
        Node pre = null;
        Node r = headSlow;
        System.out.println("z---" + r.val);
        Node next= null;
        while(r != null){//这里在翻转时不能这样用会有异常因为下面会吧相关的东西给改掉！！！！！！！注意这个地方
            next = r.next;

            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;
    }
    public Node inverseLinkList(Node r,Node p){

        Node pre = null;
        System.out.println("z---" + r.val);
        Node next= null;
        while(r !=p){ //就这点区别决定了自个写的是错的，别人写的是对的 泪崩
            next = r.next;

            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }
}
