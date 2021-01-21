package com.hjb.list;

public abstract class Handler {

    public Handler nextHandler = null;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public abstract void test(Integer num);
}
