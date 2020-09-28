package com.hjb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hjb.model.User;
import com.hjb.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumeController {

    @Reference
    private UserService userService;

    @GetMapping(value = "test")
    public User test(){
        return userService.hello();
    }
}
