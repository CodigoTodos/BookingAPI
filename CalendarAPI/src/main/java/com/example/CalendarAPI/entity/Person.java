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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private PersonType personType;

    @OneToMany(targetEntity = AvailabilitySlot.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private List<AvailabilitySlot> availabilitySlotList;

}
