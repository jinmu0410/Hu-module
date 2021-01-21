package com.hjb.list;

public class CHandler extends Handler{


    public Handler nextHandler = null;
    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void test(Integer num) {
        if(num <500){
            System.out.println("cHandler 处理了");
        }else {
            System.out.println("交给 nextHandler处理");
        }
    }
}
