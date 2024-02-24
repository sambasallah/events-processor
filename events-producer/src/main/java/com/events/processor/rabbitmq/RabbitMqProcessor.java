package com.events.processor.rabbitmq;

import com.events.processor.processor.ProducerProcessor;
import com.events.processor.event.dto.EventMessage;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProcessor implements ProducerProcessor {
    @Override
    public void process(EventMessage eventMessage) {

    }
}
