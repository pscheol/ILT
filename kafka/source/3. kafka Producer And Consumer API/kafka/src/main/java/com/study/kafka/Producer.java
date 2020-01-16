package com.study.kafka;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    public static void main(String[] args) throws Exception {
        kafkaProducer();
    }

    private static void kafkaProducer() throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("block.on.buffer.full", "true");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("In > ");
            String msg = scan.nextLine();
            if (msg.equals("END"))
                break;

            ProducerRecord<String, String> recording = new ProducerRecord<String, String>("hello-kafka", "uMsg", msg);
            try {
                producer.send(recording);
            } catch (Exception e) {

            } finally {
                producer.flush();
            }
        }
        producer.close();
    }
}
