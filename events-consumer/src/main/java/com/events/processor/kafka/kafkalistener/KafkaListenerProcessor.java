package com.events.processor.kafka.kafkalistener;

import com.events.processor.event.dto.EventMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class KafkaListenerProcessor {

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerProcessor.class);

    @KafkaListener(topics = {"events"}, groupId = "events-processor-consumer-grp", autoStartup = "${listen.auto.start:false}")
    public void process(ConsumerRecord<UUID, EventMessage> consumerRecord) {
        LOGGER.info("EVENT: ID {}, VALUE {}, OFFSET {}, PARTITION {}", consumerRecord.key(), consumerRecord.value(), consumerRecord.offset(), consumerRecord.partition() );
    }

}
