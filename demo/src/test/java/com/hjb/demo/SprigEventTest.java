package com.hjb.demo;

import com.hjb.demo.spring.event.Config;
import com.hjb.demo.spring.event.NameService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SprigEventTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        NameService nameService = context.getBean(NameService.class);
        nameService.register("zhs");
    }
}
