package cn.zyc;

/**
 * description: SyncDemo <br>
 * date: 2020/5/18 19:05 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class SyncDemo {
    private SyncDemo() {
    }

    private static SyncDemo syncDemo;

    public static SyncDemo getinstance() {
        if (syncDemo == null) {
            synchronized (SyncDemo.class) {
                if (syncDemo == null) {
                    syncDemo = new SyncDemo();
                }
            }
        }
        return syncDemo;
    }


    public static void main(String[] args) {

        SyncDemo.getinstance();
    }
}
