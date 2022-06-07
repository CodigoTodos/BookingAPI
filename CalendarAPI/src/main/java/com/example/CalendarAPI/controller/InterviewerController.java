package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.dto.InterviewerDTO;
import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.service.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/interviewers")
public class InterviewerController {

    @Autowired
    private InterviewerService interviewerService;

    @GetMapping
    public List<InterviewerDTO> findAll() {
        return interviewerService.findAll().stream().map(InterviewerDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InterviewerDTO findById(@PathVariable final Long id) {
        return new InterviewerDTO(interviewerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Interviewer> create(@RequestBody InterviewerDTO interviewerDTO) {
        return interviewerService.create(interviewerDTO.getInterviewer());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        interviewerService.delete(id);
    }

    @PutMapping
    public InterviewerDTO update(@RequestBody final InterviewerDTO interviewerDTO) {
        return new InterviewerDTO(interviewerService.update(interviewerDTO.getInterviewer()));

    }


}
