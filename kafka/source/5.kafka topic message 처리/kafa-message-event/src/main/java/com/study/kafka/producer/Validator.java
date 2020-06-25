package com.study.kafka.producer;

import com.google.gson.Gson;
import com.study.kafka.model.CheckNumber;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Validator implements Producer {
    private final KafkaProducer<String, String> producer;
    private final String validTopic;
    private final String invalidTopic;
    private final int startNum;
    private final int endNum;

    public Validator(String servers, String validTopic, String invalidTopic, int startNum, int endNum) {
        this.producer = new KafkaProducer<String, String>(Producer.createConfig(servers));
        this.validTopic = validTopic;
        this.invalidTopic = invalidTopic;
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    public void produce(String message) {

        ProducerRecord<String, String> pr = null;
        Gson gson = new Gson();
        CheckNumber chkNum = null;

        System.out.println("data : " + message);
        try {
            chkNum = gson.fromJson(message, CheckNumber.class);

            System.out.println("fromJson : " + chkNum.getName() + ", nunber=" + chkNum.getNumber());

            String topic = validate(Integer.valueOf(chkNum.getNumber()), this.startNum, this.endNum) ? this.validTopic : this.invalidTopic;
            String resultMsg = this.startNum + "~" + this.endNum + " number is " + topic;

            chkNum.setResultMsg(resultMsg);

            pr = new ProducerRecord<String, String>(topic, gson.toJson(chkNum));

            producer.send(pr);

        } catch (Exception e) {
            System.out.println("Exception...");
            pr = new ProducerRecord<String, String>(this.invalidTopic, message);
            producer.send(pr);
            return;
        }
    }

    private boolean validate(int src, int startNum, int endNum) {
        return (src >= startNum && src <= endNum);
    }
}
