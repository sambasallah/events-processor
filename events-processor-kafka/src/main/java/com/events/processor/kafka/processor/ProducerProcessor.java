package com.events.processor.kafka.processor;

import com.events.processor.event.dto.EventMessage;

public interface ProducerProcessor {
    boolean process(EventMessage eventMessage);
}
