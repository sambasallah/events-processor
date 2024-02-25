package com.events.processor.repository;

import com.events.processor.data.EventRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EventRecordRepository extends JpaRepository<EventRecord, BigInteger> {

    @Modifying
    @Transactional
    @Query("UPDATE EventRecord er SET er.status = :status WHERE er.eventID = :eventID")
    void updateEventRecordsStatus(@Param("status") String status, @Param("eventID") String eventID);
}
