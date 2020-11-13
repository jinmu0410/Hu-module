package com.hjb.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerDemo {

    @KafkaListener(topics = "demo",groupId = "consume_group_1")
    public void listen(ConsumerRecord<?,?> record, Acknowledgment ack){
        log.info("consume_group_1消费组收到消息：{},offset:{}" , record.value(),record.offset());
        ack.acknowledge();
    }

    @KafkaListener(topics = "demo",groupId = "consume_group_2")
    public void listen_1(ConsumerRecord<?,?> record, Acknowledgment ack){
        log.info("consume_group_2消费组收到消息：{},offset:{}" , record.value(),record.offset());
        ack.acknowledge();
    }
}
