package com.example.CalendarAPI.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @OneToMany(targetEntity = CandidateAvailabilitySlot.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private List<CandidateAvailabilitySlot> candidateAvailabilitySlotsList;

}
