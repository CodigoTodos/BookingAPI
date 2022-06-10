package com.example.CalendarAPI;

import com.example.CalendarAPI.configuration.ConfigurationTest;
import com.example.CalendarAPI.entity.Interviewer;
import com.example.CalendarAPI.entity.InterviewerAvailabilitySlot;
import com.example.CalendarAPI.repository.InterviewerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Ines", response.getName()),
                () -> assertEquals(7, response.getInterviewerAvailabilitySlotsList().size())
        );
    }

    @Test
    public void testFindAllInterviewers() {

        Interviewer response1 = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInes(), Interviewer.class);
        Interviewer response2 = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerIngrid(), Interviewer.class);

        int recordCount = interviewerRepository.findAll().size();
        assertEquals(2, recordCount);
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
        restTemplate.put(HTTP_LOCALHOST + port + INTERVIEWERS + "{id}", getInterviewerInesUpdate(), 1);
        Interviewer interviewer = interviewerRepository.findById(1L).get();
        assertAll(
                () -> assertNotNull(interviewer),
                () -> assertEquals("Ines Maria", interviewer.getName()),
                () -> assertEquals(3, interviewer.getInterviewerAvailabilitySlotsList().size())
        );
    }

    @Test
    public void testDeleteInterviewerById() {
        Interviewer response = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerInes(), Interviewer.class);
        int recordCount = interviewerRepository.findAll().size();
        assertEquals(1, recordCount);
        restTemplate.delete(HTTP_LOCALHOST + port + INTERVIEWERS + "/{id}", 1);
        assertEquals(0, interviewerRepository.findAll().size());

    }

    @Test
    public void findInterviewerByIdNotExistsShouldReturnError() {

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(HTTP_LOCALHOST + port + INTERVIEWERS + "/1", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void addInterviewerWithNoNameShouldReturnError() {

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerWithNoName(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testAddInterviewerWithSlotsErrorsShouldReturnInterviewerJustWithTheValidSlots() {

        Interviewer response = restTemplate.postForObject(HTTP_LOCALHOST + port + INTERVIEWERS, getInterviewerWithSlotsErrors(), Interviewer.class);

        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Bruno", response.getName()),
                () -> assertEquals(1, response.getInterviewerAvailabilitySlotsList().size())
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

    public Interviewer getInterviewerIngrid() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("13:00"), LocalTime.parse("14:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("14:00"), LocalTime.parse("15:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("15:00"), LocalTime.parse("16:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("17:00"), LocalTime.parse("18:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.TUESDAY, LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.TUESDAY, LocalTime.parse("14:00"), LocalTime.parse("15:00")));
        return new Interviewer("Ingrid", availabilitySlots);
    }

    public Interviewer getInterviewerInesUpdate() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("09:00"), LocalTime.parse("10:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        return new Interviewer("Ines Maria", availabilitySlots);
    }

    public Interviewer getInterviewerWithNoName() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("09:30"), LocalTime.parse("10:30")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:15")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        return new Interviewer("", availabilitySlots);
    }

    public Interviewer getInterviewerWithSlotsErrors() {
        List<InterviewerAvailabilitySlot> availabilitySlots = new ArrayList<InterviewerAvailabilitySlot>();
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("09:30"), LocalTime.parse("10:30")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:15")));
        availabilitySlots.add(new InterviewerAvailabilitySlot(DayOfWeek.MONDAY, LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        return new Interviewer("Bruno", availabilitySlots);
    }
}
