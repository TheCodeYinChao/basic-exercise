package cn.zyc.string;

/**
 * description: StringDemo <br>
 * date: 2020/5/20 12:00 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *  <a href="https://blog.csdn.net/qq_34490018/article/details/82110578">链接博客</a>
 *
 *
 *  这里面涉及到一个值传递和引用传递的例子结论
 *（ 1）基本数据类型传值，对形参的修改不会影响实参；
 * （2）引用类型传引用，形参和实参指向同一个内存地址（同一个对象），所以对参数的修改会影响到实际的对象；
 * （3）String, Integer, Double等immutable的类型特殊处理，可以理解为传值，最后的操作不会修改实参对象。
 *
 */
public class StringDemo {
    public static void main(String[] args) {


        String s1 = new String("1212");

        String s2 = new String("1212");

        System.out.println(s1 == s2);//false

        String s3 = "1212";
        System.out.println(s2 == s3);//false
        String s4 = "1212";
        System.out.println(s3 == s4);//true

        /**
         *  s1 先查找字符串常量池中有无 1212  没有则常量池创建1212 然后 复制对象到堆中 并将堆中的地址指向 s1
         *
         *  s2 中查找 池中有1212 ，但是因为是new 则copy 对象到堆中 并将堆中的地址指向s2
         *
         *  S3 则查找翅中有1212 则直接指向 常量池中字符的地址 如果没有的话则直接新建常量字符到池中 并返回池中地址的引用给s3
         */

        String s5 ="1"+"2"+"1"+"2";

        System.out.println(s4 == s5);//true

        /**
         * **********************************
         * s5  *------->* 字符常量池（1212） *
         * **********************************
         * s5 使用包含常量的字符串连接创建是也是常量，编译期就能确定了，直接入字符串常量池，当然同样需要判断是否已经存在该字符串
         */

        String s6="1"+"2"+new String("1")+"2";
        System.out.println(s5 == s6);//false
        System.out.println(s4 == s6);//false s6 不是常量池的地址

        /**
         *  s6 当使用“+”连接字符串中含有变量时，也是在运行期才能确定的。
         *  首先连接操作最开始时如果都是字符串常量，编译后将尽可能多的字符串常量连接在一起，
         *  形成新的字符串常量参与后续的连接（可通过反编译工具jd-gui进行查看）。
         *
         *  接下来的字符串连接是从左向右依次进行，对于不同的字符串，
         * 首先以最左边的字符串为参数创建StringBuilder对象（可变字符串对象），
         * 然后依次对右边进行append操作，最后将StringBuilder对象通过toString()方法转换成String对象
         * （注意：中间的多个字符串常量不会自动拼接）。
         *
         *  实际上的实现过程为：String s6=new StringBuilder(“12”).append(new String(“1”)).append(“2”).toString();
         *   当使用+进行多个字符串连接时，实际上是产生了一个StringBuilder对象和一个String对象
         */
        String s7 = s6.intern(); //如果池中有则返回池中字符引用

        System.out.println(s5 == s7);//true


        String s8 = new String("1") + new String("1");

        System.out.println(s8 == s8.intern()); //1.6 false 1.7 true
        /**
         * JDK6中的常量池是放在永久代的，永久代和Java堆是两个完全分开的区域。而存在变量使用“+”连接而来的的对象存在Java堆中，
         * 且并未将对象存于常量池中，当调用 intern 方法时，如果常量池中已经该字符串，则返回池中的字符串；
         * 否则将此字符串添加到常量池中，并返回字符串的引用。所以结果为false。
         *
         *JDK7中，字符串常量池已经被转移至Java堆中，开发人员也对intern 方法做了一些修改。
         * 因为字符串常量池和new的对象都存于Java堆中，为了优化性能和减少内存开销，当调用 intern 方法时，
         * 如果常量池中已经存在该字符串，则返回池中字符串；否则直接存储堆中的引用，也就是字符串常量池中存储的是指向堆里的对象。
         * 所以结果为true
         *
         *
         */
    }
}
