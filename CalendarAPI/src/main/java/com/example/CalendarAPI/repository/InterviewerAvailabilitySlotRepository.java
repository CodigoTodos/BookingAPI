package com.example.CalendarAPI.repository;

import com.example.CalendarAPI.entity.InterviewerAvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerAvailabilitySlotRepository extends JpaRepository<InterviewerAvailabilitySlot, Long> {

}
