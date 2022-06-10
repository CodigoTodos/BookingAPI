package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/interviewers")
public class InterviewerController {

    @Autowired
    private InterviewerService interviewerService;

    @GetMapping
    public List<Interviewer> findAll() {
        return interviewerService.findAll();
    }

    @GetMapping("/{id}")
    public Interviewer findById(@PathVariable final Long id) {
        return interviewerService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Interviewer create(@RequestBody @Validated Interviewer interviewer) {
        return interviewerService.create(interviewer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        interviewerService.delete(id);
    }

    //@PutMapping
    //public Interviewer update(@RequestBody final Interviewer interviewer) {
    //    return interviewerService.update(interviewer);
    //}

    @PutMapping("/{id}")
    public Interviewer update(@RequestBody Interviewer interviewer, @PathVariable long id) {
        return interviewerService.update(id, interviewer);
    }


}
