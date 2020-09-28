package com.hjb.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String test() {
        return "hello world!";
    }
}
