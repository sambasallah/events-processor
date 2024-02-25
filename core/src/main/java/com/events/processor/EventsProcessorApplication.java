package com.events.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class EventsProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsProcessorApplication.class, args);
    }

}
