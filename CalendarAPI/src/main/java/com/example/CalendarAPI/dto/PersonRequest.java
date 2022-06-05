package com.example.CalendarAPI.dto;


import com.example.CalendarAPI.entity.Person;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PersonRequest {
    
    private Person person;

}
