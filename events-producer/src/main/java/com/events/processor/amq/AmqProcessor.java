package com.events.processor.amq;

import com.events.processor.dto.EventMessage;
import com.events.processor.processor.ProducerProcessor;
import org.springframework.stereotype.Service;

@Service
public class AmqProcessor implements ProducerProcessor {

    @Override
    public void process(EventMessage eventMessage) {

    }


}
