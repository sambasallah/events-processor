package com.events.processor.rabbitmq;

import com.events.processor.dto.EventMessage;
import com.events.processor.processor.ProducerProcessor;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProcessor implements ProducerProcessor {
    @Override
    public void process(EventMessage eventMessage) {

    }
}
