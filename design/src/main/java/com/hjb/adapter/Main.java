package com.hjb.adapter;

public class Main {
    public static void main(String[] args) {
        SystemAdapter systemAdapter = new SystemAdapter();

        SystemAImpl systemA = new SystemAImpl();
        boolean check = systemAdapter.check(systemA);
        if(check){
            System.out.println("输出适配A");
            System.out.println(systemAdapter.handler(systemA));
        }
    }
}
