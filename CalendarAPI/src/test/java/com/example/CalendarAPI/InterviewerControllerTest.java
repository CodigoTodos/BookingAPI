package com.example.CalendarAPI;

import com.example.CalendarAPI.configuration.ConfigurationTest;
import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.entity.InterviewerAvailabilitySlot;
import com.example.CalendarAPI.repository.InterviewerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InterviewerControllerTest extends ConfigurationTest {

    public static final String HTTP_LOCALHOST = "http://localhost:";
    public static final String INTERVIEWERS = "/interviewers/";

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Test
    public void testAddInterviewer() {

        Interviewer response = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInes(), Interviewer.class);
        assertEquals(1, response.getId());
        assertEquals("Ines", response.getName());
        assertEquals(7, response.getInterviewerAvailabilitySlotsList().size());
    }

    @Test
    public void testFindInterviewerById() {
        Interviewer response = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInes(), Interviewer.class);
        Interviewer interviewer = restTemplate.getForObject(HTTP_LOCALHOST + port + INTERVIEWERS + "/{id}", Interviewer.class, 1);
        assertAll(
                () -> assertNotNull(interviewer),
                () -> assertEquals(1, interviewer.getId()),
                () -> assertEquals("Ines", interviewer.getName())
        );
    }

    @Test
    public void testUpdateInterviewer() {
        Interviewer response = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInes(), Interviewer.class);
        restTemplate.put(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInesUpdate());
        Interviewer interviewer = restTemplate.getForObject(HTTP_LOCALHOST + port + INTERVIEWERS + "/{id}", Interviewer.class, 1);
        assertAll(
                () -> assertNotNull(interviewer),
                () -> assertEquals("Ines Maria", interviewer.getName()),
                () -> assertEquals(3, interviewer.getInterviewerAvailabilitySlotsList().size())
        );
    }

    public Interviewer getInterviewerInes() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("09:00"), LocalTime.parse("10:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("13:00"), LocalTime.parse("14:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("14:00"), LocalTime.parse("15:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("15:00"), LocalTime.parse("16:00")));
        return new Interviewer("Ines", availabilitySlots);
    }

    public Interviewer getInterviewerInesUpdate() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("09:00"), LocalTime.parse("10:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        return new Interviewer("Ines Maria", availabilitySlots);
    }
}
