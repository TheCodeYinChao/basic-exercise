package cn.zyc.template.bak;

/**
 * @author zyc
 * @date 2019/2/26 14:49
 * @Description:
 */
public abstract  class Game {
   abstract void 热身();
   abstract void 开始();
    abstract void 结束();
    public void plan(){
        热身();
        开始();
        结束();
    }

}
