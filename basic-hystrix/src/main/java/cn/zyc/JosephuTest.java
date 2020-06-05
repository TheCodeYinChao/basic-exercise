package cn.zyc;

/**
 * @Author wenxiaoYa
 * @Date 2020/05/30  14:14
 **/
public class JosephuTest {
    public static void main(String[] args) {
        JosephuTest josephuTest = new JosephuTest();
        Person person = new Person(12, 19, "张三");
        Person person1 = new Person(2, 15, "李四");
        Person person2 = new Person(1, 19, "王五");
        Person person3 = new Person(4, 10, "小明");
        Person person4 = new Person(3, 21, "琉璃");

/*        linkedQueue.add(person);
        linkedQueue.add(person1);
        linkedQueue.add(person2);
        linkedQueue.add(person3);
        linkedQueue.add(person4);*/

        int i ;
        josephuTest.add(person);
        josephuTest.add(person1);
        josephuTest.add(person2);
        josephuTest.add(person3);
        josephuTest.add(person4);
//        josephuTest.add(person5);


        josephuTest.foreach();

        System.out.println("==============================");
        josephuTest.del(6);
        josephuTest.foreach();

    }

    Person first;

    /**
     * 添加
     *
     * @param data
     */
    public void add(Person data) {
        if (first == null) {
            first = data;
            first.next = data;
            return;
        }

        Person person = first;
        while (person.next != first) {
            if (person.id == data.id) {
                System.out.println("添加的id已存在，添加失败");
                return;
            }
            person = person.next;
        }

        person.next = data;
        data.next = first;

    }

    /**
     * 删除节点
     * @param id
     */
    public void del(int id){
        if (first == null){
            System.out.println("链表为空 删除失败");
            return;
        }
        Person first = this.first;//保持头节点

        Person person = new Person(first.id,first.age,first.name);
        person.setNext(first.next);
        Person pro = null;
        for(int i =0 ; i < id ;){
            if(i==id-1){
                pro = person;
            }
            int i1 = i++;
            if(i1 == id){
                pro.setNext(person.next);
                break;
            }
            person = person.next;
        }
    }


    public void foreach(){
        if (first == null){
            System.out.println("环形链表为空...");
            return;
        }

        Person person = first;
        do {
            System.out.println(person);
            person = person.next;
        }while (person != first);
    }


}

class Person {
    int id;

    int age;

    String name;

    Person next;

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }


    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
