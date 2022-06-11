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
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "interviewer_availability",
            joinColumns = {@JoinColumn(name = "interviewer_id")},
            inverseJoinColumns = {@JoinColumn(name = "interviewerAvailabilitySlot_id")})
    private List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList;

    //@OneToMany(targetEntity = InterviewerAvailabilitySlot.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    //private List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList;

    public Interviewer(String name, List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList) {
        this.name = name;
        this.interviewerAvailabilitySlotsList = interviewerAvailabilitySlotsList;
    }
}
