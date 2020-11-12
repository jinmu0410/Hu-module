package com.hjb.decorator;

public class BigBigShopDecorator extends ShopDecorator{

    public BigBigShopDecorator(Compent compent) {
        super(compent);
    }

    @Override
    public void sell() {
        super.sell();
        ss();
    }

    public void ss(){
        System.out.println("第二轮融资");
        System.out.println("开始卖卫星，小星星");
    }
}
