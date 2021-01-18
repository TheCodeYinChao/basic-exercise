package demonew.链表;

/**
 * @author zyc
 * @date 2021/1/18
 * @time 23:00
 * @description :
 */
public class LinkDemo {
    private Node root;


    public static void main(String[] args) {

        LinkDemo linkDemo = new LinkDemo();

        linkDemo.add(new Node(0));
        linkDemo.add(new Node(1));
        linkDemo.add(new Node(2));
        linkDemo.add(new Node(3));

        linkDemo.forEach();

    }

    public void add(Node node){
        if(root == null){
            this.root = node;
            return;
        }
        Node n = this.root;

        for (; ;) {
            if(n.getNext() == null){
                n.setNext(node);
                break;
            }
            n = n.getNext();
        }
    }

    public void delete(int del){

    }


    public  void forEach(){
        Node next = this.root;
        while (next != null){
            System.out.println(next.getVal());
            next = next.getNext();
        }
    }



    static class  Node{
        private int val;
        private Node next;

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

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
