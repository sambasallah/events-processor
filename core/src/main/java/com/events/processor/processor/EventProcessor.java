package com.events.processor.processor;

import com.events.processor.data.EventRecord;
import com.events.processor.handler.KafkaHandler;
import com.events.processor.event.dto.EventAcknowledgeMessage;
import com.events.processor.event.dto.EventMessage;
import com.events.processor.handler.AMQHandler;
import com.events.processor.handler.RabbitMQHandler;
import com.events.processor.repository.EventRecordRepository;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Getter
@Service
@RequiredArgsConstructor
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


    private final AMQHandler amqHandler;
    private final KafkaHandler kafkaHandler;
    private final RabbitMQHandler  rabbitMQHandler;
    private final EventRecordRepository eventRecordRepository;

    public ResponseEntity<EventAcknowledgeMessage> process(EventMessage eventMessage) {
        EventRecord eventRecord = new EventRecord();
        eventRecord.setEventType(eventMessage.eventType());
        eventRecord.setEventID(eventMessage.id().toString());
        eventRecord.setStatus(EventRecord.STATUS.NOT_PROCESSED.toString());
        eventRecord.setEventData(new Gson().toJson(eventMessage));

        eventRecordRepository.save(eventRecord);

        EventAcknowledgeMessage eventAcknowledgeMessage = EventAcknowledgeMessage.builder().id(UUID.randomUUID())
                .message("Acknowledged Event")
                .build();

        CompletableFuture.runAsync(() -> {
            if(this.isEnableKafka()) {
                kafkaHandler.handle(eventMessage);
                LOGGER.info("Kafka bootstrap servers {}", bootstrapServers);
            }

            if(this.isEnableAMQ()) {
                amqHandler.handle(eventMessage);
            }

            if(this.isEnableRabbitMQ()) {
                rabbitMQHandler.handle(eventMessage);
            }

        });

        return new ResponseEntity<>(eventAcknowledgeMessage, HttpStatus.CREATED);
    }
}
