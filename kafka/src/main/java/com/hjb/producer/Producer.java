package com.hjb.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping(value = "/send")
    public void send(){
        for (int i = 0; i <10 ; i++) {
            kafkaTemplate.send("demo","hello world!");
        }
    }
}
