package com.hjb.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.hjb.model.User;


@Service(timeout = 5000,version = "1.0.0",group = "user-group",token = "123456")
public class UserServiceImpl implements UserService{
    @Override
    public User hello() {
        User user = new User();
        user.setName("张三");
        user.setAge(12);
        return user;
    }
}
