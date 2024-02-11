package com.events.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@RefreshScope
public class EventsProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsProcessorApplication.class, args);
    }

}
