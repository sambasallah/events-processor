package com.events.processor.handler;

import com.events.processor.ProducerAdapter;
import com.events.processor.dto.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaHandler {

    @Autowired
    private ProducerAdapter producerAdapter;

    public void handle(EventMessage eventMessage) {
        producerAdapter.process(eventMessage);
    }
}
