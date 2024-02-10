package com.events.processor.processor;

import com.events.processor.dto.EventAcknowledgeMessage;
import com.events.processor.dto.EventMessage;
import com.events.processor.handler.AMQHandler;
import com.events.processor.handler.KafkaHandler;
import com.events.processor.handler.RabbitMQHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class EventProcessor {

    @Value(value = "${enable.kafka}")
    private boolean enableKafka;
    @Value(value = "${enable.amq}")
    private boolean enableAMQ;
    @Value(value = "${enable.rabbitmq}")
    private boolean enableRabbitMQ;

    @Autowired
    private AMQHandler amqHandler;
    @Autowired
    private KafkaHandler kafkaHandler;
    @Autowired
    private RabbitMQHandler  rabbitMQHandler;

    public ResponseEntity<EventAcknowledgeMessage> process(EventMessage eventMessage) {

        if(this.isEnableKafka()) {
            kafkaHandler.handle(eventMessage);
        }

        if(this.isEnableAMQ()) {
            amqHandler.handle(eventMessage);
        }

        if(this.isEnableRabbitMQ()) {
            rabbitMQHandler.handle(eventMessage);
        }

        EventAcknowledgeMessage eventAcknowledgeMessage = EventAcknowledgeMessage.builder().id(UUID.randomUUID())
                .message("Acknowledged Event")
                .build();

        return new ResponseEntity<>(eventAcknowledgeMessage, HttpStatus.CREATED);
    }
}
