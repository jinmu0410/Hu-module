package com.hjb.listener;

public class LisiObserver implements Observer{
    @Override
    public void receive(String message) {
        System.out.println("李四收到消息:" + message);
    }
}
