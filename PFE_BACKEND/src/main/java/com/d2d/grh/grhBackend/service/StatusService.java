package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.Status;
import com.d2d.grh.grhBackend.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void saveStatus(Status status){
        this.statusRepository.save(status);
    }
}
