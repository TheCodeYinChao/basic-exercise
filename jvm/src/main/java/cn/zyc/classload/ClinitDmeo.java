package cn.zyc.classload;

/**
 * description: ClinitDmeo <br>
 * date: 2020/5/20 17:24 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 * <p>
 * 初始化的 的作用其实就是执行 <clinit>
 * 1子类执行时 先执行父类的<clinit>
 * <p>
 * 2 接口则不会
 */
public class ClinitDmeo {
    public static void main(String[] args) {

       /* Integer b = B.b;
        System.out.println(b); //A - A  B - B  2*/

       /* Integer a = A.a;
        System.out.println(a); //A - A   1*/
        A a = new A();
        //静态块 --- 代码块 ---属性  ----构造方法
        // 父类的静态代码块->子类的静态代码块->
        // 初始化父类的属性值/父类的普通代码块(自上而下的顺序排列)->父类的构造方法->
        // 初始化子类的属性值/子类的普通代码块(自上而下的顺序排列)->子类的构造方法

    }


}

class A {
    public A() {
        System.out.println("构造A");
    }

    {
        System.out.println("代码块 AA ");
    }

    public static Integer a = 1;

    static {
        System.out.println("A - A");
    }
}

class B extends A {
    public B() {
        System.out.println("构造B");
    }

    static {
        System.out.println("B - B");
    }

    public static Integer b = 2;
}


