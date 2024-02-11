package com.events.processor.processor;

import com.events.processor.event.dto.EventMessage;

public interface ProducerProcessor {
    void process(EventMessage eventMessage);
}
