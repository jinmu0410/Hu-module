package com.hjb.adapter;

public class SystemAImpl implements SystemA{
    @Override
    public Object workA() {
        System.out.println(" work A");
        return "A";
    }
}
