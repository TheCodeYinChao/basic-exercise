package cn.zyc.死锁;

/**
 * 死锁
 * @author qhy
 * @description
 * @createTime: 2020/5/28
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        UserAccount zhangsan = new UserAccount("zhangsan", 10000d);
        UserAccount lisi = new UserAccount("lisi", 10000d);
        //ITransfer transfer = new DeadLockTransfer();//死锁
        //ITransfer transfer = new SynchronizedDeadLockTransfer();//通过hash值来实现获取锁的顺序的一致性避免死锁
        ITransfer transfer = new ReentrantLockDeadLockTransfer();//利用显示锁的tryLock来解决避免死锁
        TransferThread t1 = new TransferThread(zhangsan, lisi, 100, transfer);
        TransferThread t2 = new TransferThread(lisi, zhangsan, 150, transfer);
        t1.start();
        t2.start();
    }

    static class TransferThread extends Thread{
        private final UserAccount from;
        private final UserAccount to;
        private final double amount;
        private final ITransfer transfer;

        public TransferThread(UserAccount from,UserAccount to,double amount,ITransfer transfer) {
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }
        @Override
        public void run() {
            try {
                transfer.transfer(from, to, amount);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
