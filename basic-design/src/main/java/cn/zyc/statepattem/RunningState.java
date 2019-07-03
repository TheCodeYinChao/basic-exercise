package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 18:03
 * Version ：1.0
 * Description:
 */
public class RunningState extends LiftState {


    void open() {
        //do nothing
    }

    void close() {
        //do nothing
    }

    void run() {
        System.out.println("电梯上下跑...");
    }

    void stop() {
        super.context.setLiftState(Context.stoppingState); //环境设置为停止状态；
        super.context.getLiftState().stop();
    }
}
