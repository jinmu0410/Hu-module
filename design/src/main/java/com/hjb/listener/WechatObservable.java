package com.hjb.listener;

import java.util.ArrayList;
import java.util.List;

public class WechatObservable implements Observable{

    private List<Observer> list;

    private String message;

    @Override
    public void register(Observer observer) {

        if(list == null){
            list = new ArrayList<>();
        }
        if(!list.contains(observer)){
            list.add(observer);
            System.out.println(observer + "注册成功");
        }
    }

    @Override
    public void remove(Observer observer) {

        if(observer != null ){
            list.remove(observer);
            System.out.println(observer + "取消关注");
        }
    }

    @Override
    public void notifyObserver(String message) {
        list.forEach(e->{
            e.receive(message);
        });
    }
}
