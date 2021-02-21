package com.hjb.demo.spring.event;

import com.hjb.demo.spring.event.MyEvent;
import org.springframework.context.ApplicationListener;

//@Component
public class ServiceListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("事件监听成功" + myEvent);
    }
}
