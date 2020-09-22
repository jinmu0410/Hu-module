package com.hjb.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    private DefaultMQProducer producer;

    @GetMapping("/test")
    public void test() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String msg = "demo msg test";
        Message sendMsg = new Message("topic1", "tag1", msg.getBytes());
        Message sendMsg1 = new Message("topic2", "tag2", msg.getBytes());
        //默认3秒超时
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                producer.send(sendMsg);
            } else {
                producer.send(sendMsg1);
            }
        }
        producer.shutdown();
    }
}
