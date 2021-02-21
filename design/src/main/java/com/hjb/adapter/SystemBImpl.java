package com.hjb.adapter;

public class SystemBImpl implements SystemB{
    @Override
    public Object workB() {
        System.out.println("work B");
        return "B";
    }
}
