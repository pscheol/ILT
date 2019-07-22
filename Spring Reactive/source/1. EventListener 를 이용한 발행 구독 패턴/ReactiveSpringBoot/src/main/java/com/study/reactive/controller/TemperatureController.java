package com.study.reactive.controller;

import com.study.reactive.domain.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@RestController
public class TemperatureController {
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @GetMapping("/temperature-stream")
    public SseEmitter events(HttpServletRequest request) {
        SseEmitter sse = new SseEmitter();
        log.info("hsa");

        clients.add(sse);

        log.info("clt size : {}", clients.size());
        sse.onTimeout(() -> clients.remove(sse));
        sse.onCompletion(() -> clients.remove(sse));
        return sse;
    }


    @Async
    @EventListener
    public void handleMessage(Temperature temperature) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        clients.forEach(sseEmitter -> {
            try {
                sseEmitter.send(temperature, MediaType.APPLICATION_JSON_UTF8);
            } catch (Exception e) {

                deadEmitters.add(sseEmitter);
            }
        });
        clients.removeAll(deadEmitters);
    }
}
