package com.example.CalendarAPI.dto;


import com.example.CalendarAPI.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonRequest {

    //private String name;
    //private PersonType personType;
    //private List<AvailabilitySlot> availabilitySlotList;

    private Person person;

    
}
