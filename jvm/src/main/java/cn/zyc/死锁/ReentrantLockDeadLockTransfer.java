package cn.zyc.死锁;

import java.util.Random;

/**
 * 利用显示锁的tryLock来解决避免死锁
 *
 * @author qhy
 * @description
 * @createTime: 2020/5/28
 */
public class ReentrantLockDeadLockTransfer implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, double amount) throws InterruptedException {
        Random random = new Random();
        while (true) {
            if (from.getLock().tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock:" + from.getLock() + "...");
                    if (to.getLock().tryLock()) {
                        try {
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(Thread.currentThread().getName() + " get lock:" + to.getLock() + " end");
                            System.out.println("transfer success amount = " + amount);
                            System.out.println(from.getName() + " account = " + from.getAccount());
                            System.out.println(to.getName() + " account = " + to.getAccount());
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
//            Thread.sleep(random.nextInt(10));//避免活锁影响效率
        }

    }

}
