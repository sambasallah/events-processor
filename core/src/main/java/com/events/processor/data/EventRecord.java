package com.events.processor.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Entity
@Data
public class EventRecord {

    public enum STATUS {
        PROCESSED,
        NOT_PROCESSED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "eventType")
    private String eventType;
    @Column(name = "eventData")
    private String eventData;
    @Column(name = "status")
    private String status;
    @Column(name = "eventID")
    private String eventID;

}
