package com.events.processor.processor;

import com.events.processor.handler.KafkaHandler;
import com.events.processor.event.dto.EventAcknowledgeMessage;
import com.events.processor.event.dto.EventMessage;
import com.events.processor.handler.AMQHandler;
import com.events.processor.handler.RabbitMQHandler;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class EventProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProcessor.class);

    @Value(value = "${enable.kafka}")
    private boolean enableKafka;
    @Value(value = "${enable.amq}")
    private boolean enableAMQ;
    @Value(value = "${enable.rabbitmq}")
    private boolean enableRabbitMQ;
    @Value(value = "${events.kafka.bootstrapServers}")
    private String bootstrapServers;

    @Autowired
    private AMQHandler amqHandler;
    @Autowired
    private KafkaHandler kafkaHandler;
    @Autowired
    private RabbitMQHandler  rabbitMQHandler;



    public ResponseEntity<EventAcknowledgeMessage> process(EventMessage eventMessage) {

        if(this.isEnableKafka()) {
            kafkaHandler.handle(eventMessage);
            LOGGER.info("Configuration bootstrap servers {}", bootstrapServers);
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
