package com.hjb.decorator;

public class ShopDecorator extends Decorator{

    public ShopDecorator(Compent compent) {
        super(compent);
    }

    @Override
    public void sell() {
        super.sell();

    }
}
