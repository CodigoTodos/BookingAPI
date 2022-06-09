package com.example.CalendarAPI.service;

import com.example.CalendarAPI.entity.Candidate;
import com.example.CalendarAPI.exceptions.InterviewCalendarException;
import com.example.CalendarAPI.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Candidate findById(final Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.CANDIDATE_NOT_FOUND + id));
    }

    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void delete(final long id) {
        final Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.CANDIDATE_NOT_FOUND + id));
        candidateRepository.delete(candidate);
    }

    public Candidate update(Candidate candidate) {
        Candidate candidateToSave = candidateRepository.findById(candidate.getId()).orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.CANDIDATE_NOT_FOUND + candidate.getId()));
        return candidateRepository.save(candidate);
    }

}
