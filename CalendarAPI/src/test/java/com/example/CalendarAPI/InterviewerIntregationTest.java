package com.example.CalendarAPI;

import com.example.CalendarAPI.entity.Interviewer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class InterviewerIntregationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/interviewers";

    @Test
    public void testAddEmployee() throws Exception {
        long id = 1;
        // prepare
        Interviewer interviewer = new Interviewer("Ines", null);

        // execute
        ResponseEntity<Interviewer> responseEntity = restTemplate.postForEntity(URL, interviewer, Interviewer.class);

        // collect Response
        int status = responseEntity.getStatusCodeValue();
        Interviewer resultinterviewer = responseEntity.getBody();

        // verify
        assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");

        //assertNotNull(resultEmployee);
        //assertNotNull(resultEmployee.getId().longValue());

    }


//    @LocalServerPort
//    private int port;
//    private String baseUrl = "http://localhost";
//    private static RestTemplate restTemplate;
//
//    @Autowired
//    private InterviewerRepository interviewerRepository;
//
//    @BeforeAll
//    public static void init() {
//        restTemplate = new RestTemplate();
//    }
//
//    @BeforeEach
//    public void setUp() {
//        baseUrl = baseUrl.concat(":").concat(port + "").concat("/interviewers");
//    }
//
//    @Test
//    public void testAddInterviewer() {
//        //List<CandidateAvailabilitySlot> candidateAvailabilitySlotsList = new List<CandidateAvailabilitySlot>();
//        long id = 1;
//        Interviewer interviewer = new Interviewer(id, "Ines", null);
//        Interviewer response = restTemplate.postForObject(baseUrl, interviewer, Interviewer.class);
//        assertEquals("Ines", response.getName());
//    }


}
