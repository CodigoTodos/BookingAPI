package com.example.CalendarAPI.service;

import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.entity.InterviewerAvailabilitySlot;
import com.example.CalendarAPI.exceptions.InterviewCalendarException;
import com.example.CalendarAPI.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public Interviewer create(Interviewer interviewer) {
        if (!StringUtils.hasText(interviewer.getName())) {
            throw new InterviewCalendarException(InterviewCalendarException.INTERVIEWER_WITH_BLANK_NAME);
        }
        List<InterviewerAvailabilitySlot> validatedSlots = validateSlots(interviewer.getInterviewerAvailabilitySlotsList());
        interviewer.setInterviewerAvailabilitySlotsList(validatedSlots);
        return interviewerRepository.save(interviewer);
    }

    private List<InterviewerAvailabilitySlot> validateSlots(List<InterviewerAvailabilitySlot> interviewerAvailabilitySlotsList) {
        boolean add;
        List<InterviewerAvailabilitySlot> slotsValidates = new ArrayList<InterviewerAvailabilitySlot>();


        for (int i = 0; i < interviewerAvailabilitySlotsList.size(); i++) {
            add = true;
            InterviewerAvailabilitySlot slot = interviewerAvailabilitySlotsList.get(i);
            if (ChronoUnit.MINUTES.between(slot.getStartTime(), slot.getFinishTime()) != 60
                    || !StringUtils.endsWithIgnoreCase(slot.getStartTime().toString(), "00")
                    || !StringUtils.endsWithIgnoreCase(slot.getFinishTime().toString(), "00"))

                add = false;

            if (add)
                slotsValidates.add(slot);

        }
        return slotsValidates;
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
