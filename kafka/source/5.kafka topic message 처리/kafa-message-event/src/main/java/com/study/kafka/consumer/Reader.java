package com.study.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;


public class Reader implements Consumer {
    private final KafkaConsumer<String, String> consumer; //1. kafka consumer load
    private final String topic;

    public Reader(String servers, String groupId, String topic) {

        this.consumer = new KafkaConsumer<String, String>(Consumer.createConfig(servers, groupId));
        this.topic = topic;
    }

    @Override
    public ConsumerRecords<String, String> consume() {
        this.consumer.subscribe(Collections.singleton(this.topic)); //2. topic publish
        return consumer.poll(Duration.ofMillis(100)); //3. set timeout
    }
}
