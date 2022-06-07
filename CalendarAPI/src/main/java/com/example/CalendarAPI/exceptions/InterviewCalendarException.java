package com.example.CalendarAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InterviewCalendarException extends RuntimeException {

    public static final String INTERVIEWER_NOT_FOUND = "No interviewer present with id ";
    public static final String CANDIDATE_NOT_FOUND = "No candidate present with id ";

    public InterviewCalendarException(final String msg) {
        super(msg);
    }

}
