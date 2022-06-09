package com.example.CalendarAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//https://bushansirgur.in/spring-data-jpa-one-to-many-bi-directional-example/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(targetEntity = InterviewerAvailabilitySlot.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList;
    
    public Interviewer(String name, List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList) {
        this.name = name;
        this.interviewerAvailabilitySlotsList = interviewerAvailabilitySlotsList;
    }
}
