package com.hjb.decorator;

public class Test {

    public static void main(String[] args) {
        Compent compent = new ShopCompent();
        compent.sell();


        Compent compent1 = new ShopDecorator(compent);
        compent1.sell();

        System.out.println("第一次");
        Compent compent2 = new BigShopDecorator(compent1);
        compent2.sell();

        System.out.println("第二次");
        Compent compent3 = new BigBigShopDecorator(compent1);
        compent3.sell();

    }
}
