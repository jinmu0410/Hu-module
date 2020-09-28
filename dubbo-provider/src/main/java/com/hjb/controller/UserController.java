package com.hjb.controller;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.hjb.model.User;
import com.hjb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDubbo
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "hello")
    public User hello(){
        return userService.hello();
    }
}
