package com.events.processor.processor;

import com.events.processor.dto.EventMessage;

public interface ProducerProcessor {
    void process(EventMessage eventMessage);
}
