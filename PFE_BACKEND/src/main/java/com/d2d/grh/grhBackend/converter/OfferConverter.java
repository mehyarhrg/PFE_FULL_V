package com.d2d.grh.grhBackend.converter;

import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.entity.Offer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferConverter {

    public OfferDTO entityToDto(Offer offer){
        OfferDTO dto = new OfferDTO();
        dto.setOfferId(offer.getOfferId());
        dto.setOfferTitle(offer.getOfferTitle());
        dto.setOfferRef(offer.getOfferRef());
        dto.setPublishDate(offer.getPublishDate());
        dto.setOfferDescription(offer.getOfferDescription());
        dto.setStatusId(offer.getStatus() != null ? offer.getStatus().getStatusId() : 9999999L);
        dto.setStatusColor(offer.getStatus() != null ? offer.getStatus().getStatusColor():"");
        dto.setStatusName(offer.getStatus() != null ? offer.getStatus().getStatusName(): "");

        return dto;
    }

    public List<OfferDTO> entityToDto(Collection<Offer> offers){
        return offers.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
}
