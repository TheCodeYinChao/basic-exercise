package cn.zyc.template.bak;

/**
 * @author zyc
 * @date 2019/2/26 14:49
 * @Description: 策略上下文
 */
public class Context {
    private Game game;

    public Context(Game game) {
        this.game = game;
    }

    public void 游戏(){
        game.plan();
    }
}
