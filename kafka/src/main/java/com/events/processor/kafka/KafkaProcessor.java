package com.events.processor.kafka;

import com.events.processor.event.dto.EventMessage;
import com.events.processor.kafka.processor.ProducerProcessor;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProcessor implements ProducerProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessor.class.getName());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value="${events.kafka.topic-name}")
    private String kafkaTopicName;
    @Value(value="${events.kafka.send.timeout.ms}")
    private int kafkaSendTimeoutMS;



    @Override
    public boolean process(EventMessage eventMessage) {
       try {
           CompletableFuture<SendResult<String, String>> future =  kafkaTemplate
                   .send(kafkaTopicName, String.valueOf(UUID.randomUUID()), new Gson().toJson(eventMessage))
                   .orTimeout(kafkaSendTimeoutMS, TimeUnit.MILLISECONDS);
           SendResult<String, String> result = future.get();
           LOGGER.info("topic {}, offset {} , partition {}", result.getRecordMetadata().topic(),
                   result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
           LOGGER.info("KAFKA EVENT: {}", eventMessage);
           return true;

       } catch (Exception e) {
           LOGGER.info("An Exception occurred : {}", e.getMessage());
           return false;
       }
    }
}
