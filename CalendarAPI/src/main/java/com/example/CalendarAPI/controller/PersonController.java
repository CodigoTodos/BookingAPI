package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.dto.PersonRequest;
import com.example.CalendarAPI.entity.Person;
import com.example.CalendarAPI.entity.PersonType;
import com.example.CalendarAPI.repository.AvailabilitySlotRepository;
import com.example.CalendarAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    @PostMapping("/interviewers")
    public Person saveInterviewers(@RequestBody PersonRequest request) {
        request.getPerson().setPersonType(PersonType.Interviewer);
        return personRepository.save(request.getPerson());
    }

    @PostMapping("/candidates")
    public Person saveCandidates(@RequestBody PersonRequest request) {
        request.getPerson().setPersonType(PersonType.Candidate);
        return personRepository.save(request.getPerson());
    }


//@Autowired
//private PersonService personService;

//@PostMapping("/persons")
//public ResponseEntity<Person> savePerson(@RequestBody Person person) {
//    return personService.savePerson(person);
//}

}
