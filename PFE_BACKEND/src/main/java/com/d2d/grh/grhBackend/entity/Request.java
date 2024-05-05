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
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;

    private String requestTitle;
    private Date deadLine;
    private String description;
    private boolean urgent;
    private String postRequired;

    @ManyToOne
    private User user;

    @ManyToOne
    private Status status;
}
