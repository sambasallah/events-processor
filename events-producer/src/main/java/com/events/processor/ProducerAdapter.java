package com.events.processor;

import com.events.processor.dto.EventMessage;
import com.events.processor.kafka.KafkaProcessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProducerAdapter {

    @Value(value = "${enable.kafka}")
    private boolean enableKafka;
    @Value(value = "${enable.amq}")
    private boolean enableAMQ;
    @Value(value = "${enable.rabbitmq}")
    private boolean enableRabbitMQ;

    @Autowired
    private KafkaProcessor kafkaProcessor;

    public void process(EventMessage eventMessage) {

        if(this.isEnableKafka()) {
            kafkaProcessor.process(eventMessage);
        }

        if(this.isEnableAMQ()) {

        }

        if(this.isEnableRabbitMQ()) {

        }

    }
}