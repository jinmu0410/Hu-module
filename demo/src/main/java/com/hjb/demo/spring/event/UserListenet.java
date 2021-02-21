package com.hjb.demo.spring.event;

import com.hjb.demo.spring.event.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class UserListenet {

    @EventListener
    @Order(1)
    public void nameService(MyEvent myEvent){
        System.out.println("111注解收到事件" + myEvent);
    }

    @EventListener
    @Order(0)
    public void nameService1(MyEvent myEvent){
        System.out.println("222注解收到事件" + myEvent);
    }
}
