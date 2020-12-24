package cn.zyc.垃圾收集;

import org.junit.Test;
import sun.nio.ch.DirectBuffer;
import java.nio.*;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * dsc: GarbageCollection
 * date: 2020/12/22 19:12
 * author: zyc
 * 一个Reference类型的对象实例（简称R）总体来说有四种状态
 * 1. Active状态
 * 	即R的referent属性指向的（堆中）对象（下简称r）还有除了referent之外的其他强引用，尚未回收. 处于active状态的R会被GC器特别对待，因为GC器会监视它.一旦r的可达性发生了变化，变化到了某种状态以至于符	合回收策略的话，则R会改变它的状态到pending或者inactive.至于到底是变化到pending还是inactive，	  这取决于当时R创建的时候有没有传入ReferenceQueue(引用队列，本文简称RQ)这个参数进入构造器（参看下面的源码2, 即是否使用的是源码2的第七行的那个构造器，简称构造器2）. 如果使用了构造器2的话，就会将R加入到pending list（也就是Reference类中的static属性pending，它是该pending list的头结点），新创建的Reference对象实例都是处于active状态的.
 *
 * 2. Pending状态
 * 	即表示该R已经是pending list的一员了. 就等着ReferenceHandler线程把它移入相应的ReferenceQueue了。注意，如果R当时创建的时候没有调用构造器1的话，则R永远不会处于本状态.
 * 3. Enqueued状态
 * 	即表示该R已经是ReferenceQueue的一员了. 这个ReferenceQueue就是当时创建R时调用构造器2时传入的第二个参数（也就是R的成员变量queue）. 当R离开ReferenceQueue的时候，R的状态就变成了inactive状态（也就是下面的第四种状态）. 注意，和Pending状态一样，对于调用构造器1创建的R而言，是永远不会处于本状态的.
 * 4. Inactive状态
 * 不会再做任何事情了, 一般的，如果R变成了本状态的话，它的状态将永远不会改变.
 *
 * 如果Reference实例处于上述4种不同状态下，它的next属性和queue属性将有不同的值
 * 1. 处于Active状态下的话，queue就是创建R时调用构造器2的时候传入的queue（如果创建R是通过调用构造器1来创建的话，则queue是ReferenceQueue.NULL）. next是null
 * 2. 处于Pending状态下的话,queue是构造器2时时传入的queue, 而next是this（毕竟参与刻画pending list的是discovered属性，而不是next, next是刻画RQ的）
 * 3. 处于Enqueued状态的话，则queue属性是ReferenceQueue.ENQUEUED，next是本Reference对象实例在RQ中的下一个元素， 如果本Reference对象实例是RQ中最后一个元素的话，则next=this
 * 4. 处于Inactive状态的话, 则queue等于ReferenceQueue.NULL; 而next属性 = this
 *
 * https://yfsyfs.github.io/2019/07/05/java-lang-ref-Reference-%E6%BA%90%E7%A0%81%E8%A7%A3%E8%AF%BB/#more
 * https://www.iflym.com/index.php/code/201609050002.html
 * 有了上面不同状态下queue、next的不同，GC器很容易通过检测一个Reference实例对象的next属性来看看它是不是处于Active状态, 如果next属性是null的话，则它处于Active状态，否则就是处于其他三种状态. 为什么要区分Active和其他三种状态? 因为Active状态的话，GC器是要监视它的~ 所以要区别对待.
 *
 *
 *
 * 特殊的reference对象都是被jvm专门处理的,因此这里就相应的工作流程和referencequeue之间的协作进行梳理.
 *
 * 其内部提供2个构造函数,一个带queue,一个不带queue.其中queue的意义在于,我们可以在外部对这个queue进行监控.即如果有对象即将被回收,那么相应的reference对象就会被放到这个queue里.我们拿到reference,就可以再作一些事务.
 *
 * 而如果不带的话,就只有不断地轮训reference对象,通过判断里面的get是否返回null(phantomReference对象不能这样作,其get始终返回null,因此它只有带queue的构造函数).这两种方法均有相应的使用场景,取决于实际的应用.
 * 如weakHashMap中就选择去查询queue的数据,来判定是否有对象将被回收.而ThreadLocalMap,则采用判断get()是否为null来作处理
 *
 *
 */
public class GarbageCollection {
    public static void main(String[] args) throws IOException {
        Reference reference;
        FinalDemo f = new FinalDemo();
        /**
         * //这里将f置为空切断了引用 gc是会回收，第一次会调用finalize() 方法.这里面我们可以做一些事情
         * 擦除 或者为这个f从重新建立引用，防止回收
         *
         * 主要是对gc可以验证我们的类作一些额外的工作。
         * finalize() 方法里用本地方法调用它 什么意思？ 就是 用c c++去调用
         *
         */
        f=null;


        System.gc();
        System.in.read();

    }

    /**
     * 学着使用 referencequeue
     */
    @Test
    public void enqueue虚引用(){
        ReferenceQueue referenceQueue = new ReferenceQueue();
        Object object = new Object();
        System.out.println(object);
        PhantomReference  p = new PhantomReference(object,referenceQueue);
        object =null;
        System.gc();
        //这里有个问题是 gc完成之后才会去执行这个东西，这是时候poll出来的reference已经是空的啦
        Reference poll = referenceQueue.poll();
        Object o = poll.get();
        System.out.println(o);
    }

    /**
     * 监控对象的销毁
     * @throws InterruptedException
     */
    @Test
    public void monitorObj() throws InterruptedException {
        boolean isRun = true;
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        boolean finalIsRun = isRun;
        new Thread() {
            public void run() {
                while (finalIsRun) {
                    Object obj = referenceQueue.poll();//这里是一个监控，当referencequeue中有值则证明对象被回收啦
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
        isRun = false;
    }

    /**
     * 堆外内存的销毁
     * 前提示需要发生gc
     */
    @Test
    public void directBUffer(){
//        DirectBuffer directBuffer = new DirectByteBuffer(100);
        //这里面集成一个Cleaner，当发生gc时如果这个buffer的强引用被回收则清除堆外内存
        ByteBuffer.allocate(10*1024*1024);//分配10M的内存 这个使用的是HeapByteBuffer 堆内存不用手动释放

//        而unsafe获取的内存属于堆外内存jvm不会帮我们回收
//        如何释放堆外内存  1 重写finalize 方法 jdk9已经没有这个啦  2 Cleaner 3 PhantomReference 利用这个监控方法对象是否被回收，如果回收去清除堆外内存

    }

}
