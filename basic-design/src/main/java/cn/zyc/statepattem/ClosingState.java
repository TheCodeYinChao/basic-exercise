package cn.zyc.statepattem;

import javax.sound.midi.Soundbank;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 18:02
 * Version ：1.0
 * Description:
 */
public class ClosingState extends LiftState {
    void open() {
        super.context.setLiftState(Context.openningState); //置为门敞状态
        super.context.getLiftState().open();
    }

    void close() {
        System.out.println("电梯关闭");
    }

    void run() {
        super.context.setLiftState(Context.runningState); //设置为运行状态；
        super.context.getLiftState().run();
    }

    void stop() {
        super.context.setLiftState(Context.stoppingState); //设置为停止状态；
        super.context.getLiftState().stop();
    }
}
