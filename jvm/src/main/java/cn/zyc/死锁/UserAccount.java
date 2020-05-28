package cn.zyc.死锁;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qhy
 * @description
 * @createTime: 2020/5/28
 */
public class UserAccount {

    private String name;

    private Double account;

    private ReentrantLock lock = new ReentrantLock();

    public UserAccount(String name,Double account){
        this.name = name;
        this.account = account;
    }

    public void flyMoney(Double amount){
        account = account - amount;
    }

    public void addMoney(Double amount){
        account = account + amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }
}
