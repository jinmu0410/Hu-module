package com.hjb.demo;

import com.hjb.demo.spring.impor.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config.class);
        context.refresh();
        UserService userService = context.getBean(UserService.class);
        userService.handle();
    }

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(UserConfig.class);
        context.refresh();
        UserService userService = context.getBean(UserService.class);
        userService.handle();
    }

    @Test
    public void test3(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config2.class);
        context.refresh();
        UserService userService = (UserService) context.getBean("service_1");
        userService.handle();
    }

    @Test
    public void test4(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Config3.class);
        context.refresh();
        UserService userService = (UserService) context.getBean("com.hjb.demo.spring.impor.UserService");
        userService.handle();
    }

}
