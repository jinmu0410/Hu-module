package com.hjb.listener;

public class ZhsObserver implements Observer{
    @Override
    public void receive(String message) {
        System.out.println("张三收到消息：" + message);
    }
}
