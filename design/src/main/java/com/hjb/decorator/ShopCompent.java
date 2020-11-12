package com.hjb.decorator;

public class ShopCompent implements Compent{
    @Override
    public void sell() {
        System.out.println("商店开始卖水果。。。");
    }
}
