package com.d2d.grh.grhBackend.dto;

import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.Skills;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class CandidateDTO {

    private int candidateId;

    private String firstname;
    private String lastname;
    private String email;
    private String message;
    private long phone;
    private String cvFilePath;

    private int status_id;
    private StatusDTO statusDTO;
    private List<FeedBackDTO> feedBackDTO;
    private Collection<Skills> skills;
    private List<OfferDTO> offers;
}
