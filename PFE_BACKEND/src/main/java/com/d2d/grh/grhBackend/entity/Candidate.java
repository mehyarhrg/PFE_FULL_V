package com.d2d.grh.grhBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int candidateId;

    private String firstname;
    private String lastname;
    private String email;
    private String message;
    private String username;
    private String password;
    private long phone;
    private String cvFilePath;

    private int status_id;

    @ManyToOne
    @JoinColumn(name = "status_id", updatable = false,insertable = false)
    private Status status;

    @OneToMany(mappedBy = "candidate")
    private List<FeedBack> feedBacks;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Skills> skills = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Offer> offers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<NotificationCandidate> notificationCandidates = new ArrayList<>();

//    @OneToMany(mappedBy = "candidate")
//    private List<Interview> interviews;

}
