package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.entity.Candidate;
import com.example.CalendarAPI.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> findAll() {
        return candidateService.findAll();
    }

    @GetMapping("/{id}")
    public Candidate findById(@PathVariable final Long id) {
        return candidateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate create(@RequestBody Candidate candidate) {
        return candidateService.create(candidate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        candidateService.delete(id);
    }

    @PutMapping
    public Candidate update(@RequestBody final Candidate candidate) {
        return candidateService.update(candidate);

    }
}
