package cn.zyc.array;

/**
 * description: Linked 链表
 *
 * 单向链表
 *  操作首尾
 *
 * 双向链表：  　 实现双端队列
 *
 *  对于单项链表，我们如果想在尾部添加一个节点，
 *  那么必须从头部一直遍历到尾部，找到尾节点，
 *  然后在尾节点后面插入一个节点。这样操作很麻烦，如果我们在设计链表的时候多个对尾节点的引用
 *
 *  有序链表
 *
 *
 *  有序链表和无序数组组合排序
 *
 *  比如有一个无序数组需要排序，前面我们在讲解冒泡排序、选择排序、插入排序这三种简单的排序时，需要的时间级别都是O(N2)。
 *　现在我们讲解了有序链表之后，对于一个无序数组，我们先将数组元素取出，一个一个的插入到有序链表中，然后将他们从有序链表中一个一个删除，重新放入数组，那么数组就会排好序了。和插入排序一样，如果插入了N个新数据，那么进行大概N2/4次比较。但是相对于插入排序，每个元素只进行了两次排序，一次从数组到链表，一次从链表到数组，大概需要2*N次移动，而插入排序则需要N2次移动，
 *  效率肯定是比前面讲的简单排序要高，但是缺点就是需要开辟差不多两倍的空间，而且数组和链表必须在内存中同时存在，如果有现成的链表可以用，那么这种方法还是挺好的。
 * date: 2020/11/2 17:03 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Linked {


    /**双端链表*/
    class CustomLinked{
        Node head;
        Node tail;
        int size;


        class Node{
            private Object data;
            private Node next;

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }

    }
}
