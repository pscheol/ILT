package com.study.reactive.services;

import com.study.reactive.domain.Temperature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TemeratureSensor {
    //이벤트를 시스템에 발행하는 클래스
    private final ApplicationEventPublisher publisher;
    private final Random random = new Random();
    //스케쥴 스레드 풀
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public TemeratureSensor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void startProcessing() {
        this.executor.schedule(this::probe, 1, TimeUnit.SECONDS);
    }

    private void probe() {
        double temperature = 16 + random.nextGaussian() * 10;
        publisher.publishEvent(new Temperature(temperature));
        //랜덤한 지연시간(0~5초)을 두고 다음 읽기 스케줄을 예약
        executor.schedule(this::probe, random.nextInt(5000), TimeUnit.MICROSECONDS);

    }
}
