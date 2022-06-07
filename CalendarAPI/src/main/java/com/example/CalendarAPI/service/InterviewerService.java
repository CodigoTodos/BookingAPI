package com.example.CalendarAPI.service;

import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.exceptions.InterviewCalendarException;
import com.example.CalendarAPI.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewerService {

    @Autowired
    private InterviewerRepository interviewerRepository;

    public List<Interviewer> findAll() {
        return interviewerRepository.findAll();
    }

    public Interviewer findById(final Long id) {
        return interviewerRepository.findById(id)
                .orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.INTERVIEWER_NOT_FOUND + id));
    }

    public ResponseEntity<Interviewer> create(Interviewer interviewer) {
        return new ResponseEntity<Interviewer>(interviewerRepository.save(interviewer), HttpStatus.CREATED);
    }

    public void delete(final long id) {
        final Interviewer interviewer = interviewerRepository.findById(id)
                .orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.INTERVIEWER_NOT_FOUND + id));
        interviewerRepository.delete(interviewer);
    }

    public Interviewer update(Interviewer interviewer) {
        Interviewer interviewerToSave = interviewerRepository.findById(interviewer.getId()).orElseThrow(() -> new InterviewCalendarException(InterviewCalendarException.INTERVIEWER_NOT_FOUND + interviewer.getId()));
        return interviewerRepository.save(interviewer);
    }
}
