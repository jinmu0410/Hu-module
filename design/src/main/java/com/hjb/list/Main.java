package com.hjb.list;

public class Main {

    public static void main(String[] args) {
        AHandler aHandler = new AHandler();
        BHandler bHandler = new BHandler();
        CHandler cHandler = new CHandler();
        aHandler.setNextHandler(bHandler);
        bHandler.setNextHandler(cHandler);

        aHandler.test(500);
    }
}
