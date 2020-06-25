package com.springkafka.kafka.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class ConsumerService {

    private String LastMsg;

    @KafkaListener(topics = "${kafka.topic.source-topic}")
    public void receiver(@Payload String message, @Headers MessageHeaders headers) {
        headers.keySet().forEach(key -> log.info("key={}, value={}", key, headers.get(key)));
        log.info("Received Message : {}", message);
        setLastMsg(message);

    }
}
