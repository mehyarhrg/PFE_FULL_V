package com.d2d.grh.grhBackend.models;

import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.dto.StatusDTO;
import com.d2d.grh.grhBackend.entity.FeedBack;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy {

    private int candidateId;
    private String firstname;
    private String lastname;
    private String email;
    private long phone;
    private List<FeedBack> feedBacks;
    private OfferDTO offer;
    private String cvFilePath;
    private StatusDTO status;

}
