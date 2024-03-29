package com.events.processor.controller;

import com.events.processor.event.dto.Alive;
import com.events.processor.event.dto.EventAcknowledgeMessage;
import com.events.processor.event.dto.EventMessage;
import com.events.processor.processor.EventProcessor;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class.getName());

    private final EventProcessor processor;

    @PostMapping("/event")
    public ResponseEntity<EventAcknowledgeMessage> event(@RequestBody EventMessage event) {
        LOGGER.info("Event: {}", new Gson().toJson(event));
        return processor.process(event);
    }

    @GetMapping("/is-alive")
    public ResponseEntity<Alive> alive() {
        Alive alive = Alive.builder().isAlive(Boolean.TRUE).message("Service is up")
                .build();
        return new ResponseEntity<>(alive, HttpStatus.OK);
    }
}
