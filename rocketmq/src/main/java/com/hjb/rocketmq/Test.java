package com.hjb.rocketmq;

import com.alibaba.fastjson.JSON;
import com.hjb.rocketmq.common.User;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Test {

    @Autowired
    private DefaultMQProducer producer;

    @GetMapping("/test")
    public void test() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        User user = new User();
        user.setName("测试");
        user.setAge(10);
        Message sendMsg = new Message("topic1", "tag1", JSON.toJSONString(user).getBytes());

        Message sendMsg1 = new Message("topic2", "tag2", JSON.toJSONString(user).getBytes());
        //默认3秒超时
        List<Message> messageList = new ArrayList<>();
        List<Message> messageList1 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                messageList.add(sendMsg);
            } else {
                messageList1.add(sendMsg1);
            }
        }
        producer.send(messageList);
        producer.send(messageList1);
        producer.shutdown();
    }
}
