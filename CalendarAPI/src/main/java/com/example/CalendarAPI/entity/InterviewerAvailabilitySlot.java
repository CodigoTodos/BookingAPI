package com.example.CalendarAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
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
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime startTime;
    private LocalTime finishTime;

    @ManyToOne(targetEntity = Interviewer.class)
    @JsonBackReference
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;
    
    public InterviewerAvailabilitySlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime finishTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.finishTime = finishTime;

    }
}
