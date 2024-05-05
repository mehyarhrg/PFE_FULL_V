package com.d2d.grh.grhBackend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstname;
    private String lastname;

    @Column(unique = true)

    private String email;

    @Column(unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String address;
    private String city;
    private String country;
    private int postalCode;
    private String aboutMe;
    private String departmentt;
    private Date creationDate;

    @ManyToOne
    private Department department;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "interviewer")
    private List<Interview> interviews;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<NotificationUser> notificationUsers = new ArrayList<>();



}
