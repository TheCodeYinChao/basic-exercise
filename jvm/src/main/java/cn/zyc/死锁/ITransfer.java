package cn.zyc.死锁;

public interface ITransfer {

    public void transfer(UserAccount from, UserAccount to, double amount) throws InterruptedException;
}
