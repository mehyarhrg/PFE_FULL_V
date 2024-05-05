package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.converter.OfferConverter;
import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.OfferCategory;
import com.d2d.grh.grhBackend.entity.OfferStatus;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.exception.OfferNotFoundException;
import com.d2d.grh.grhBackend.repository.OfferRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferConverter offerConverter;

    @Autowired
    public OfferService(OfferRepository offerRepository, OfferConverter offerConverter) {
        this.offerRepository = offerRepository;
        this.offerConverter = offerConverter;
    }



    public void saveOffer(String offer, String offerCategory, String offerStatus) throws JsonProcessingException {
        Offer newOffer = new ObjectMapper().readValue(offer, Offer.class);
        OfferCategory category = new ObjectMapper().readValue(offerCategory, OfferCategory.class);
        OfferStatus status = new ObjectMapper().readValue(offerStatus, OfferStatus.class);
        System.out.println(status);
        newOffer.setCategory(category);
        newOffer.setStatus(status);
        System.out.println(newOffer);
        this.offerRepository.save(newOffer);
    }

    public List<OfferDTO> findAllOffers() {
        List<Offer> offersFromEntity =  this.offerRepository.findAll();
        return this.offerConverter.entityToDto(offersFromEntity);
    }

    public void deleteOffer(Integer offerId){
        this.offerRepository.deleteById(offerId);
    }

    public void updateOfferStatus(Integer offerId, OfferStatus status){
        Offer existingOffer = this.offerRepository.findById(offerId).orElseThrow(()-> new OfferNotFoundException("no offer match with this ID !! "));
        existingOffer.setStatus(status);
        this.offerRepository.save(existingOffer);
    }

    public  OfferDTO getOfferDetails(int offerId){
        return this.offerConverter.entityToDto(this.offerRepository.findById(offerId).get());
    }


}
