package cn.zyc.垃圾收集;

/**
 * dsc: FinalDemo
 * date: 2020/12/23 16:29
 * author: zyc
 */
public class FinalDemo  {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize..........");
        super.finalize();// 在java9中已被废弃
    }
}
