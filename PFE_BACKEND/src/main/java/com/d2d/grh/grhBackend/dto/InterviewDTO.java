package com.d2d.grh.grhBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {
    private String interviewDate;
    private String interviewHour;
    private String offerName;
    private String interviewer;
    private String lienGoogleMeet;

}
