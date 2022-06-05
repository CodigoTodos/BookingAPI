package com.example.CalendarAPI.service;

import com.example.CalendarAPI.dto.PersonRequest;
import com.example.CalendarAPI.entity.Person;
import com.example.CalendarAPI.entity.PersonType;
import com.example.CalendarAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public ResponseEntity<Person> saveInterviewers(PersonRequest request) {
        request.getPerson().setPersonType(PersonType.Interviewer);
        return new ResponseEntity<>(personRepository.save(request.getPerson()), HttpStatus.CREATED);
    }

    public ResponseEntity<Person> saveCandidates(PersonRequest request) {
        request.getPerson().setPersonType(PersonType.Candidate);
        return new ResponseEntity<>(personRepository.save(request.getPerson()), HttpStatus.CREATED);
    }
}
