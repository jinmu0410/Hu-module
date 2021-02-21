package com.hjb.demo.spring.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    private String name;

    public MyEvent(Object source) {
        super(source);
    }

    public MyEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

}
