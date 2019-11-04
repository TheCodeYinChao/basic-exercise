package cn.zyc.jvm;

/**
 * -Xss20m
 *
 * 32 默认2gb限制
 *
 * 正常 情况深度能到到1000-2000
 * 增大 设置  和更换64位虚拟机
 *
 */
public class VMStackSof {
    private int stackLength =1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        VMStackSof vm = new VMStackSof();
        try {
            vm.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length :"+vm.stackLength);
            throw e;
        }
    }

}
