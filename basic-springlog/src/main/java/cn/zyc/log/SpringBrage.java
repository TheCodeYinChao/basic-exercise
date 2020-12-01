package cn.zyc.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *抽象与实现解耦 ，使它们都可以独立的变化。
 *
 * 两种不同行为 的接口
 * 例如  ：
 *      画图形， 我们 画一个 原型 的绿色的圆
 *      涉及多个属性 1 圆型 2 颜色的选择
 *      我们通过桥接将圆型类别与 显色类别互相解耦并独自变化
 *
 */

public class SpringBrage {
    public static void main(String[] args) {
        Log log = LogFactory.getLog("spr brage");
        log.info("spring");
    }
}
