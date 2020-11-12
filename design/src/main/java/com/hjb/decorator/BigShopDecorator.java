package com.hjb.decorator;

public class BigShopDecorator extends ShopDecorator{

    public BigShopDecorator(Compent compent) {
        super(compent);
    }

    @Override
    public void sell() {
        super.sell();
        ss();
    }

    public void ss(){
        System.out.println("一轮融资之后，店铺又扩展了");
        System.out.println("开始卖汽车火箭了");
    }
}
