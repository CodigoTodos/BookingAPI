package com.example.CalendarAPI.controller;

import com.example.CalendarAPI.dto.CandidateDTO;
import com.example.CalendarAPI.entity.Candidate;
import com.example.CalendarAPI.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<CandidateDTO> findAll() {
        return candidateService.findAll().stream().map(CandidateDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CandidateDTO findById(@PathVariable final Long id) {
        return new CandidateDTO(candidateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Candidate> create(@RequestBody CandidateDTO candidateDTO) {
        return candidateService.create(candidateDTO.getCandidate());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        candidateService.delete(id);
    }

    @PutMapping
    public CandidateDTO update(@RequestBody final CandidateDTO candidateDTO) {
        return new CandidateDTO(candidateService.update(candidateDTO.getCandidate()));

    }
}
