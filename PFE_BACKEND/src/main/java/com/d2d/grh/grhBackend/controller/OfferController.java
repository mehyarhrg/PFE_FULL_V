package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.dto.OfferDTO;
import com.d2d.grh.grhBackend.entity.Offer;
import com.d2d.grh.grhBackend.entity.OfferStatus;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.service.OfferService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferService offerService;


    @PostMapping("/saveOffer")
    public void saveOffer(@RequestParam("postObject") String offerObject,
                          @RequestParam("offerCategoryObject") String offerCategory,
                          @RequestParam("offerStatusObject") String offerStatus)throws JsonProcessingException {

        this.offerService.saveOffer(offerObject, offerCategory, offerStatus);
    }

    @GetMapping("/allOffers")
    public List<OfferDTO> findAllOffers(){
        return this.offerService.findAllOffers();
    }

    @DeleteMapping("/deleteOffer/{offerId}")
    public void deleteOffer(@PathVariable Integer offerId){
        this.offerService.deleteOffer(offerId);
    }

    @PostMapping("/updateOfferStatus/{offerId}")
    public void updateOfferStatus(@PathVariable Integer offerId,@RequestBody OfferStatus status){
        this.offerService.updateOfferStatus(offerId, status);
    }

    @GetMapping("/jobDetails/{offerId}")
    public OfferDTO getOfferDetails(@PathVariable int offerId){
        return this.offerService.getOfferDetails(offerId);
    }





}
