package demonew.链表;

/**
 * dsc: ReversalLink
 * date: 2021/1/19 19:24
 * author: zyc
 */
public class ReversalLink {

    public static void main(String[] args) {

        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        print(node);

        Node head = reversal(node);
        print(head);


    }


    public static Node reversal(Node head){


        return null;
    }



    public static void print(Node head){
        if(head == null){return;}
        while (head  != null){
            System.out.println(head.getVal());
            head = head.next;
        }
    }






    static class Node{
        private int val;
        private Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
