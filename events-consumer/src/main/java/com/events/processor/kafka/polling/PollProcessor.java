package com.events.processor.kafka.polling;


import com.events.processor.processor.ConsumerProcessor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


@Component
public class PollProcessor implements ConsumerProcessor , ApplicationRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerProcessor.class);

    @Value("${events.kafka.bootstrapServers}")
    private String bootstrapServers;

    @Override
    public void process() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        consumerProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1000");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProps);

        AtomicBoolean closed = new AtomicBoolean(false);
        kafkaConsumer.subscribe(List.of("events"));

        while(!closed.get()) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : records) {
                LOGGER.info("EVENT: ID {}, VALUE {}, OFFSET {}, PARTITION {}", consumerRecord.key(), consumerRecord.value(), consumerRecord.offset(), consumerRecord.partition() );
            }
            kafkaConsumer.commitSync();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        process();
    }
}
