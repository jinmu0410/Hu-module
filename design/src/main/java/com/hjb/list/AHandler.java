package com.hjb.list;

public class AHandler extends Handler{

    public Handler nextHandler = null;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void test(Integer num) {
        if(num <200){
            System.out.println("aHandler 处理了");
        }else {
            System.out.println("交给 nextHandler处理");
            nextHandler.test(num);
        }
    }
}
