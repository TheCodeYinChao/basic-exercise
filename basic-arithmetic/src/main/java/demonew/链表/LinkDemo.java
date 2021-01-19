package demonew.链表;

/**
 * @author zyc
 * @date 2021/1/18
 * @time 23:00
 * @description : 头插 o（1） 尾插 o(n)
 */
public class LinkDemo {
    private Node head;


    public static void main(String[] args) {

        LinkDemo linkDemo = new LinkDemo();

        System.out.println("-------------------------ADD");
        linkDemo.add(0);
        linkDemo.add(1);
        linkDemo.add(2);
        linkDemo.add(3);

        System.out.println("-------------------------DEL");
        linkDemo.delete(2);
        linkDemo.forEach();

        System.out.println("-------------------------FIND");
        Node node = linkDemo.find(3);
        System.out.println(node.getVal());

    }


    /**
     * 头插 O（1）
     * @param val
     */
    public void add(int val){
        Node newNode = new Node(val);
        if(head == null){
            this.head = newNode;
            return;
        }
        newNode.setNext(head);
        this.head = newNode;
    }


    /**
     * 删除分两种情况
     *  1 删除无重复 模拟的删除是这种
     *  2 删除有重复
     * @param del
     */
    public void delete(int del){

        Node p = this.head; //头节点
        if(p == null){return;}
        Node q = null;

        while (p != null && p.getVal() != del){
             q = p;
             p = p.getNext();
        }

        if (p == null) return;//没有找着值为del的

        if (q == null) {//q等于空第一个就相等
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }

    public Node find(int val){
        if (head == null) {
            return null;
        }
        Node a = head;
        while (a.getVal()!=val){
            a = a.getNext();
        }
        return a;
    }


    public  void forEach(){
        Node next = this.head;
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
