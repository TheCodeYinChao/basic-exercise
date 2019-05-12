package com.zyc.sprdemo;

/**
 * Created by Admin on 2019/5/12.
 */
@Service
public class ServiceImpl {
    private IGoods goodsDao;
    //设值注入
    @ManagerAnnotation(test=BedGoodsImpl.class)
    public void setUserdao(IGoods goodsDao) {
        this.goodsDao = goodsDao;
    }
    public void buy_Test(){
        goodsDao.buy();
    }
}
