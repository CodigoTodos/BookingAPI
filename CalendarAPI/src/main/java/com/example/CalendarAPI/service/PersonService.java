package com.example.CalendarAPI.service;

import com.example.CalendarAPI.entity.Person;
import com.example.CalendarAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public ResponseEntity<Person> savePerson(Person person) {
        return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
    }

}
