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
public class CandidateAvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime finishTime;

    @ManyToOne(targetEntity = Candidate.class)
    @JsonBackReference
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
