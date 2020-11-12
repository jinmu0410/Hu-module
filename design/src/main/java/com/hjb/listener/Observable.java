package com.hjb.listener;

/**
 * 被观察者接口
 */
public interface Observable {

    void register(Observer observer);

    void remove(Observer observer);

    void notifyObserver(String message);
}
