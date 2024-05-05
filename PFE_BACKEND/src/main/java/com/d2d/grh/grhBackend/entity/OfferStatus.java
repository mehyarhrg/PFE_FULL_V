package com.d2d.grh.grhBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusId;

    private String statusName;
    private String statusColor;

    @OneToMany(mappedBy = "status")
    private List<Offer> offers;

}
