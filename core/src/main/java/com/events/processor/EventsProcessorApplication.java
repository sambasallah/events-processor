package com.events.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class EventsProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsProcessorApplication.class, args);
    }

}
