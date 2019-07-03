package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 18:03
 * Version ：1.0
 * Description:
 */
public class StoppingState extends LiftState {
    void open() {
        super.context.setLiftState(Context.openningState);
        super.context.getLiftState().open();
    }

    void close() {
        //do nothing;
    }

    void run() {
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    void stop() {
        System.out.println("电梯停止了...");
    }
}
