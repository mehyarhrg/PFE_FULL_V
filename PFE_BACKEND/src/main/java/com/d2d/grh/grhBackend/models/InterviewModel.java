package com.d2d.grh.grhBackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewModel {
    private String interviewDate;
    private String interviewHour;
    private String candidateName;
    private String subject;
    private String interviewerName;
    private String candidateEmail;
    private String lienGoogleMeet;
}
