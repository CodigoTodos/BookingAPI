package com.example.CalendarAPI.dto;

import com.example.CalendarAPI.entity.AvailabilitySlot;
import com.example.CalendarAPI.entity.PersonType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonResponse {
    private Long id;
    private String name;
    private PersonType personType;
    private List<AvailabilitySlot> availabilitySlotList;

}
