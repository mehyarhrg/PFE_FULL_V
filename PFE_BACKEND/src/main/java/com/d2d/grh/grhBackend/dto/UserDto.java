package com.d2d.grh.grhBackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String address;
    private String city;
    private String country;
    private int postalCode;
    private String aboutMe;
    private String department;
    private Date creationDate;
    private String roleName;

}
