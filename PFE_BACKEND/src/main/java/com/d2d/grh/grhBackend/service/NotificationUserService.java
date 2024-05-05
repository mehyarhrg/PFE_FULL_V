package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.NotificationUser;
import com.d2d.grh.grhBackend.repository.NotificationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationUserService {

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public String pushNotificationRequestToRh(NotificationUser notificationUser){
        this.notificationUserRepository.save(notificationUser);
        this.userService.findUserByUsername2(notificationUser.getToUser()).getNotificationUsers().add(notificationUser);
        return "notification sent !";
    }

    public List<NotificationUser> getMyNotifications(String username){
        return this.notificationUserRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .filter(notif->notif.getToUser().equals(username)).collect(Collectors.toList());
    }
}
