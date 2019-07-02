package com.study.kafka;

import com.study.kafka.consumer.Reader;
import com.study.kafka.producer.Validator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Scanner;


public class Main {
    //private static final Logger log = LoggerFactory.getLogger(Reader.class);

    public static void main(String[] args) throws Exception {
        String servers = args[0];
        String groupId = args[1];
        String sourceTopic = args[2];
        String validTopic = args[3];
        String invalidTopic = args[4];

        Scanner scan = new Scanner(System.in);
        System.out.print("input start number ->");
        int startNum = scan.nextInt();
        System.out.print("input end number ->");
        int endNum = scan.nextInt();

        System.out.println();

        Reader reader = new Reader(servers, groupId, sourceTopic);
        Validator validator = new Validator(servers, validTopic, invalidTopic, startNum, endNum);

        while (true) {
            ConsumerRecords<String, String> consumerRecords = reader.consume();
            for (ConsumerRecord<String, String> record : consumerRecords) {
                if (record.value() != null && !record.value().equals(""))
                    validator.produce(record.value());
            }
        }
    }
}
