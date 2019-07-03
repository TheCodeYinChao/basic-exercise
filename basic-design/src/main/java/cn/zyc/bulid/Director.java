package cn.zyc.bulid;

/**
 * @author zyc
 * @date 2019/3/22 17:08
 * @Description:
 */
public class Director {
    private Builder builder;
    //这里可以结合工厂模式
    public void construct(String type){
        if("one".equals(type)){
            builder=new ConcreteBuilderOne();
            builder.buildOne();
            builder.buildTwo();
            builder.retrievePro();
        }else if("two".equals(type)){
            builder=new ConcreteBuilderTwo();
            builder.buildOne();
            builder.buildTwo();
            builder.retrievePro();
        }else{

        }
    }
}
