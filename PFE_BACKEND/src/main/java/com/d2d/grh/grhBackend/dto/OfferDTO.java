package com.d2d.grh.grhBackend.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OfferDTO implements Serializable {

    private int offerId;
    private String offerTitle;
    private String offerRef;
    private Date publishDate;
    private String offerDescription;

    private Long statusId;
    private String statusName;
    private String statusColor;
}
