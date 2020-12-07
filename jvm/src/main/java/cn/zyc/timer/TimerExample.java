package cn.zyc.timer;

import org.junit.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * dsc: TimerExample
 * 平衡二查堆
 * 也就是说TimerTask 在堆中的位置其实是通过nextExecutionTime
 * 来决定的。nextExecutionTime 越小，那么在堆中的位置越靠近根，越有可能先被执行。
 * 而nextExecutionTime意思就是下一次执行开始的时间。
 *
 * 这里有个限制就是这玩意儿必须等堆顶端元素执行完成之后才能继续下一个执行 spring 之前的定死任务默认就是这个
 * date: 2020/12/7 18:41
 * author: zyc
 */
public class TimerExample {

    @Test
    public void timer() throws IOException {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("A");
            }
        },1000);

        System.in.read();
    }
}
