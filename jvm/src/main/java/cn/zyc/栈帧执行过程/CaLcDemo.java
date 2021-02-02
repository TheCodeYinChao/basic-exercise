package cn.zyc.栈帧执行过程;

/**
 * dsc: CaLcDemo
 * date: 2021/2/1 15:37
 * author: zyc
 */
public class CaLcDemo {

    public int calc(){
        int a = 100;
        int b =200;
        int c = 300;
        return (a+b)*c;
    }

    public static void main(String[] args) {
        new CaLcDemo().calc();
    }
}
