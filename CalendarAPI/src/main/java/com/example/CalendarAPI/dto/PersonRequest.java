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

    private Person person;

}
