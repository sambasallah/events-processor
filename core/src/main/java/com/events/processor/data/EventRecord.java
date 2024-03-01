package com.events.processor.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EventRecord {

    public enum STATUS {
        PROCESSED,
        NOT_PROCESSED;
    }

    @Id
    @Column(name = "eventID")
    private String eventID;
    @Column(name = "eventType")
    private String eventType;
    @Column(name = "eventData")
    private String eventData;
    @Column(name = "status")
    private String status;

}
