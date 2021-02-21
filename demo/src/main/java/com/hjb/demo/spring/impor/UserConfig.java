package com.hjb.demo.spring.impor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService getUser(){
        return new UserService();
    }
}
