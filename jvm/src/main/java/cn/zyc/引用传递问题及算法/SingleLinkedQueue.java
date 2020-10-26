package cn.zyc.引用传递问题及算法;

/**
 * @Author wenxiaoYa
 * @Date 2020/05/29  14:33
 **/
public class SingleLinkedQueue {

    public static void main(String[] args) {
        SingleLinkedQueue linkedQueue = new SingleLinkedQueue();
        Student student = new Student(12, 19, "张三");
        Student student1 = new Student(2, 15, "李四");
        Student student2 = new Student(1, 19, "王五");
        Student student3 = new Student(4, 10, "小明");
        Student student4 = new Student(3, 21, "琉璃");

        linkedQueue.addSort(student);
        linkedQueue.addSort(student1);
        linkedQueue.addSort(student2);
        linkedQueue.addSort(student3);
        linkedQueue.addSort(student4);

  /*      linkedQueue.add(student);
        linkedQueue.add(student1);
        linkedQueue.add(student2);
        linkedQueue.add(student3);
        linkedQueue.add(student4);*/

        linkedQueue.foreach();

        System.out.println("==============================");

/*        System.out.println(linkedQueue.find(62));

        linkedQueue.update(new Student(2, 22, "王五"));
        System.out.println("==============================");
        linkedQueue.foreach();

        linkedQueue.delete(2);


        System.out.println(linkedQueue.length());
        System.out.println("======================================");
        System.out.println(linkedQueue.findList(2));
//        System.out.println();
//        linkedQueue.foreach();

*/

//        linkedQueue.reversal();
        linkedQueue.reversal3();
        linkedQueue.foreach();
    }

    Student first = new Student(0, 0, null);

    /**
     * 添加
     *
     * @param student
     */
    public void add(Student student) {
        Student s = first;

        if (s.next == null) {
            s.next = student;
            return;
        }

        while (s.next != null) {
            s = s.next;
        }

        s.next = student;
    }

    /**
     * 添加+排序
     *
     * @param student
     */
    public void addSort(Student student) {
        Student s = first;

        if (s.next == null) {
            s.next = student;
            return;
        }

        while (s.next != null) {
            if (student.id == s.next.id) {
                System.out.println("添加内容已存在");
                return;
            }
            if (student.id < s.next.id) {
                Student ss = s.next;
                s.next = student;
                s.next.next = ss;
                return;
            }
            s = s.next;
        }

        s.next = student;
    }

    /**
     * 修改
     *
     * @param student
     */
    public void update(Student student) {
        Student s = first;

        if (s.next == null) {
            System.out.println("链表为空");
            return;
        }

        while (s.next != null) {
            if (s.next.id == student.id) {
                s.next.userName = student.userName;
                s.next.age = student.age;
                return;
            }
            s = s.next;
        }

        System.out.println("未找到，修改失败");
        return;
    }

    /**
     * 删除节点
     *
     * @param id
     */
    public void delete(int id) {
        Student s = first;

        if (s.next == null) {
            System.out.println("删除失败，链表为空");
            return;
        }

        while (s.next != null) {
            if (s.next.id == id) {
                s.next = s.next.next;
                return;
            }
            s = s.next;
        }

        System.out.println("删除失败，未找到值");
        return;
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    public Student find(int id) {
        Student s = first;

        if (s.next == null) {
            System.out.println("链表为空");
            return null;
        }

        while (s.next != null) {
            if (s.next.id == id) {
                return s.next;
            }
            s = s.next;
        }

        System.out.println("未找到。。。。");
        return null;
    }

    /**
     * 遍历
     */
    public void foreach() {

        if (first.next == null) {
            System.out.println("链表为空...");
            return;
        }
        Student s = first.next;
        while (s != null) {

            System.out.println(s);
            s = s.next;
        }
    }

    /**
     * 链表长度
     *
     * @return
     */
    public int length() {
        int length = 0;
        Student s = first;

        if (s.next == null) {
            return 0;
        }

        while (s.next != null) {
            length++;
            s = s.next;
        }

        return length;
    }

    /**
     * 查找到数第count个节点
     *
     * @param count
     * @return
     */
    public Student findList(int count) {
        Student s = first.next;

        int length = length();

        if (count > length || count <= 0) {
            System.out.println("输入数据不合法");
            return null;
        }

        for (int i = 0; i < length - count; i++) {
            s = s.next;
        }

        return s;
    }

    public void reversal() {
        int length = length();

        if (length <= 1) return;

        Student s = new Student(0, 0, null);

        while (first.next != null) {
            if (s.next == null) {
                Student next1 = first.next;
                Student ns = new Student(next1.id, next1.age, next1.userName);
                s.next = ns;
            } else {
                Student next = s.next;
                Student next1 = first.next;
                Student ns = new Student(next1.id, next1.age, next1.userName);
                s.next = ns;
                s.next.next = next;
            }
            first = first.next;
        }
        first = s;
    }


    public void reversal3() {
        Student first = this.first;
        while (first.next != null) {
            first = first.next;
        }
    }

    public void reversal1() {
        int length = length();

        if (length <= 1) return;

        Student s = new Student(0, 0, null);
        Student ne = null;
        while (first.next != null) {
            if (s.next == null) {
                ne = first.next;
//                ne = s.next;
                s.next = ne;
            } else {
//                ne = s.next;
                //这块明显有问题
                ne.next = first.next;
                ne = ne.next;
//                ne =  s.next;

            }
            first = first.next;
        }
        first = s;

    }

}

class Student {
    int id;
    int age;
    String userName;
    Student next;

    public Student(int id, int age, String userName) {
        this.id = id;
        this.age = age;
        this.userName = userName;
    }

//

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", userName='" + userName +
                '}';
    }
}