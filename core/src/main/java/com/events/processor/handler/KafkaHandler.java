package com.events.processor.handler;

import com.events.processor.data.EventRecord;
import com.events.processor.event.dto.EventMessage;
import com.events.processor.kafka.KafkaProcessor;
import com.events.processor.repository.EventRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class KafkaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaHandler.class);
    private final KafkaProcessor kafkaProcessor;
    private final EventRecordRepository eventRecordRepository;

    public void handle(EventMessage eventMessage) {
        boolean sent = kafkaProcessor.process(eventMessage);
        LOGGER.info("KAFKA Event SENT = {}", sent);
        if(sent) {
            LOGGER.info("Updating event status to PROCESSED");
            eventRecordRepository.updateEventRecordsStatus(EventRecord.STATUS.PROCESSED.toString(),eventMessage.id().toString());
        }
    }
}
