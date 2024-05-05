package com.d2d.grh.grhBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer feedBackId;
    private String feedBackText;
    private int candidate_id;
    private String feedBackType;

    @ManyToOne
    @JoinColumn(name = "candidate_id", updatable = false, insertable = false)
    @JsonBackReference
    private Candidate candidate;

}
