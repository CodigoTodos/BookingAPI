package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.dto.PersonRequest;
import com.example.CalendarAPI.entity.Person;
import com.example.CalendarAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/interviewers")
    public ResponseEntity<Person> saveInterviewers(@RequestBody PersonRequest request) {
        return personService.saveInterviewers(request);
    }

    @PostMapping("/candidates")
    public ResponseEntity<Person> saveCandidates(@RequestBody PersonRequest request) {
        return personService.saveCandidates(request);
    }


}
