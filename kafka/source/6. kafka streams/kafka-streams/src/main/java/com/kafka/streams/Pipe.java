package com.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Pipe {
    public static void pipeStart() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe"); //kafka streams application을 유일할게 구분할 ID
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //kafka application에 접근할 broker 정보
        //데이터를 어떤 형식으로 read/write할지 결정(key-value type)
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


        //data stream으로 구성된 Topology 를 정의할 builder
        final StreamsBuilder builder = new StreamsBuilder();
        //input stream topic, output streams topic 설정
        builder.stream("streams-plaintext-input").to("streams-pipe-output"); //topic 설정

        final Topology topology = builder.build();

        final KafkaStreams streams = new KafkaStreams(topology, props);

        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            System.out.println("Pipe Topology started");
            System.out.println(topology.describe());
            latch.await();
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
