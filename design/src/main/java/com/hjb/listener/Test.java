package com.hjb.listener;

public class Test {
    public static void main(String[] args) {
        WechatObservable wechatObservable = new WechatObservable();
        LisiObserver lisi = new LisiObserver();
        ZhsObserver zhs = new ZhsObserver();
        wechatObservable.register(lisi);
        wechatObservable.register(zhs);
        wechatObservable.register(lisi);

        wechatObservable.notifyObserver("欢迎大家关注我");
        wechatObservable.remove(lisi);

        wechatObservable.notifyObserver("大家跟我一起学习");

    }
}
