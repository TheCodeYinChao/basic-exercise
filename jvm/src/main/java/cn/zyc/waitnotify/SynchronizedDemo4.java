package cn.zyc.waitnotify;// +----------------------------------------------------------------------

import java.util.LinkedList;

/**
 * 下面的代码在绝大部分时间内部运行得很正常，请问在什么情况下会出现问题？问题的根源在哪里？
 */
public class SynchronizedDemo4 {

    LinkedList list = new LinkedList();

    public  synchronized void push(Object x){
        synchronized (list){
            System.out.println("准备唤醒");
            notify();

            list.addLast(x);
//            list.notify();
            System.out.println("唤醒结束");
        }
    }

    public  synchronized Object pop() throws Exception{
        System.out.println("方法阻塞");

        System.out.println("方法阻塞结束");
        synchronized(list){
            if(list.size() <= 0){
                list.wait();
                System.out.println("方法阻塞2");
//                wait();
//                System.out.println("方法阻塞结束2");
            }
            return list.removeLast();
        }

    }

    public static void main(String[] args) throws Exception {
        SynchronizedDemo4 synchronizedDemo4 = new SynchronizedDemo4();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedDemo4.pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo4.push("A");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo4.push("A");
            }
        }).start();

    }

}
    


