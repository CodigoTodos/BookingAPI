package com.example.CalendarAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class InterviewerAvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime finishTime;

    @ManyToOne(targetEntity = Interviewer.class)
    @JsonBackReference
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;


}
