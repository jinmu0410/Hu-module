package com.hjb.service;

import com.hjb.model.User;

public class UserServiceMock implements UserService{
    @Override
    public User hello() {
        return new User("error",1);
    }
}
