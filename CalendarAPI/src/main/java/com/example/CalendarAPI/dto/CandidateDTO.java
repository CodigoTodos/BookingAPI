package com.example.CalendarAPI.dto;

import com.example.CalendarAPI.entity.Candidate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CandidateDTO {
    private Candidate candidate;
}
