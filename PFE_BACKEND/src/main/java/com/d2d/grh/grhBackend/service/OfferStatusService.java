package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.OfferStatus;
import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.repository.OfferStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferStatusService {

    @Autowired
    private OfferStatusRepository offerStatusRepository;

    public void saveStatus(OfferStatus status){
        this.offerStatusRepository.save(status);
    }

}
