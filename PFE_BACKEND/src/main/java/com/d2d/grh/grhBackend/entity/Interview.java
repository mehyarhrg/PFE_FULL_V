package com.d2d.grh.grhBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long interviewId;

    private String interviewDate;
    private String interviewHour;
    private String candidateName;
    private String lienGoogleMeet;
//    @ManyToOne
//    private Candidate candidate;

    @ManyToOne
    private Offer offer;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User interviewer;
}
