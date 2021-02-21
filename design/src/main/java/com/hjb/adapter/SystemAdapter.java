package com.hjb.adapter;

public class SystemAdapter implements Adapter{
    @Override
    public boolean check(Object object) {
        return object instanceof SystemA;
    }

    @Override
    public Object handler(Object object) {
        SystemA systemA = (SystemA) object;
        System.out.println("适配前");
        Object o = systemA.workA();
        System.out.println("适配后");
        return o;
    }
}
