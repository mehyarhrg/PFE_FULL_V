package com.d2d.grh.grhBackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateOfferObject {

    private String candidateUsername;
    private int offerId;
}
