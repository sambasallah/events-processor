package com.events.processor.kafka;

import com.events.processor.dto.EventMessage;
import com.events.processor.processor.ProducerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaProcessor implements ProducerProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessor.class.getName());

    @Override
    public void process(EventMessage eventMessage) {
        LOGGER.info("KAFKA EVENT: {}", eventMessage);
    }
}
