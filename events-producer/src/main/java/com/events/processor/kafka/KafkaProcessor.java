package com.events.processor.kafka;

import com.events.processor.dto.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessor.class.getName());

    public void process(EventMessage eventMessage) {
        LOGGER.info("KAFKA EVENT: {}", eventMessage);
    }
}
