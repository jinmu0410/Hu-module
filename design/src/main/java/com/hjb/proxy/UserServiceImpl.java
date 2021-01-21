package com.hjb.proxy;

public class UserServiceImpl implements UserService{
    @Override
    public void work(String name,Integer age) {
        System.out.println("开始工作");
    }
}
