package com.springkafka.kafka.controller;

import com.springkafka.kafka.service.ConsumerService;
import com.springkafka.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final ProducerService producerService;
    private final ConsumerService consumerService;

    @Autowired
    public KafkaController(ProducerService producerService, ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @GetMapping("/send")
    public String sendProducer(@RequestParam(value = "msg") String message) {
        producerService.send(message);
        return "success";
    }

    @GetMapping("/receiver")
    public String getMsg() {
        return consumerService.getLastMsg();
    }
}
