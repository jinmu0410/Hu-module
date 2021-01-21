package com.hjb.service;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String hello() {
       return "hello world www.bai";
    }

    @Override
    public String test(String name) {
        return name;
    }
}
