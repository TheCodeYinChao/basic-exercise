package cn.zyc.bulid;

/**
 * @author zyc
 * @date 2019/3/22 17:07
 * @Description:
 */
public class ConcreteBuilderTwo implements Builder {

    private Product product=new ProductTwo();
    public void buildOne(){
        //打造第一个零件
    }
    public void buildTwo(){
        //打造第二个零件
    }
    public Product retrievePro(){
        //组装出产品2
        return product;
    }
}
