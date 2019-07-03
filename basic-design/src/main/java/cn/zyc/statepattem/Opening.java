package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:59
 * Version ：1.0
 * Description:
 */
public class Opening extends LiftState {

    void close() {
    //状态修改
            super.context.setLiftState(Context.closeingState);
    //动作委托为CloseState来执行
            super.context.getLiftState().close();
    }
    void open() {
        System.out.println("电梯门开启...");
    }

    void run() {
        //do nothing
    }

    void stop() {
        //do nothing
    }
}
