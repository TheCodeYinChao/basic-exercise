package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 17:57
 * Version ：1.0
 * Description:
 */
public class Context {
    public final static Opening openningState = new Opening();
    public final static ClosingState closeingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();
    //定一个当前电梯状态
    private LiftState liftState;
    public LiftState getLiftState() {
        return liftState;
    }
    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        //把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }
    public void open(){
        this.liftState.open();
    }
    public void close(){
        this.liftState.close();
    }
    public void run(){
        this.liftState.run();
    }
    public void stop(){
        this.liftState.stop();
    }

}
