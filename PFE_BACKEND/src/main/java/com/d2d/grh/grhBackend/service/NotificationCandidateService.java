package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.repository.NotificationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationCandidateService {

    @Autowired
    private NotificationUserRepository notificationUserRepository;
}
