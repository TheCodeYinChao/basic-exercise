package cn.zyc.statepattem;

/**
 * User: zyc
 * Date: 2018-05-21
 * Time: 18:09
 * Version ：1.0
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(Context.closeingState);
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
